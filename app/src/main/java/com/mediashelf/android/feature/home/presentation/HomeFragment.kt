package com.mediashelf.android.feature.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mediashelf.android.R
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.extensions.navigate
import com.mediashelf.android.core.navigation.NavigationRouter
import com.mediashelf.android.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.loadMedia()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            homeViewModel = this@HomeFragment.homeViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        observeUiEffects()
        observeErrors()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener {

            if (it.itemId == R.id.menu_action_search) {
                findNavController().navigate(NavigationRouter.SearchRouter)
            } else if (it.itemId == R.id.menu_action_about) {
                findNavController().navigate(HomeFragmentDirections.actionAbout())
            }

            return@setOnMenuItemClickListener true
        }
    }

    private fun observeUiEffects() {
        lifecycleScope.launch {
            homeViewModel.uiEffect.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                when (it) {
                    is HomeUiEffect.NavigateToMovieDetails -> navigateToMediaDetails(it.media)
                }
            }
        }
    }

    private fun observeErrors() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.uiState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).map { it.loadFailed }.distinctUntilChanged().collectLatest {
                if (it) {
                    handleException()
                }
            }
        }
    }

    private fun navigateToMediaDetails(media: Media) {
        findNavController().navigate(NavigationRouter.MediaRouter(media))
    }

    private fun handleException() {
        Snackbar.make(
            binding.root,
            getString(R.string.label_error_occured),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.btn_try_again) {
            homeViewModel.loadMedia()
        }.show()
    }
}