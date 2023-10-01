package com.example.test_1intro.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.s16mvvmcleanachitecture.R
import com.example.s16mvvmcleanachitecture.databinding.FragmentArtsBinding

class ArtFragment: Fragment(R.layout.fragment_arts) {
    private lateinit var binding: FragmentArtsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArtsBinding.bind(view)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_artFragment_to_artDetailsFragment)
        }
    }
}