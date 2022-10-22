package com.mediashelf.android.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.extensions.dpToPx
import com.mediashelf.android.core.extensions.navigate
import com.mediashelf.android.core.navigation.NavigationRouter
import com.mediashelf.android.core.presentation.MarginItemDecoration
import com.mediashelf.android.core.utils.hideSoftKeyboard
import com.mediashelf.android.core.utils.showSoftKeyboard
import com.mediashelf.android.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupSearchView()
        setupMediaList()
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupSearchView() {
        val searchViewEditText =
            binding.mediaSearchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)

        binding.mediaSearchView.post {
            showSoftKeyboard(
                requireActivity(),
                searchViewEditText
            )
        }

        binding.mediaSearchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.searchMedia(it)
                    hideSoftKeyboard(requireActivity(), searchViewEditText)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun setupMediaList() {
        binding.searchMediaList.apply {
            adapter = SearchMediaPagingAdapter {
                navigateToMedia(it)
            }

            addItemDecoration(MarginItemDecoration(8.dpToPx(), 8.dpToPx(), 8.dpToPx(), 8.dpToPx()))
        }
    }

    private fun subscribeUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.searchResults.flowWithLifecycle(
                viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED
            ).collectLatest {
                (binding.searchMediaList.adapter as SearchMediaPagingAdapter).submitData(it)
            }
        }
    }

    private fun navigateToMedia(media: Media) {
        findNavController().navigate(NavigationRouter.MediaRouter(media))
    }
}