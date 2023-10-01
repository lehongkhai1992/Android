package com.example.test_1intro.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.s16mvvmcleanachitecture.R
import com.example.s16mvvmcleanachitecture.databinding.FragmentArtDetailBinding
import javax.inject.Inject

class ArtDetailsFragment @Inject constructor( private val glide: RequestManager) : Fragment(R.layout.fragment_art_detail) {
    private lateinit var binding: FragmentArtDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtDetailBinding.bind(view)
        binding.artimageView.setOnClickListener {
            findNavController().navigate(R.id.action_artDetailsFragment_to_imageApiFragment)
        }
        val  callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}