package com.mediashelf.android.feature.home.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mediashelf.android.BuildConfig
import com.mediashelf.android.R
import com.mediashelf.android.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupButtons()
        setupVersion()
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

    private fun setupButtons() {
        binding.privacyPolicyButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    getString(R.string.privacy_policy_link).toUri()
                )
            )
        }

        binding.acknowledgementsButton.setOnClickListener { }
    }

    private fun setupVersion() {
        binding.versionText.text = getString(R.string.label_version, BuildConfig.VERSION_NAME)
    }
}