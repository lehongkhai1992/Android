package com.example.test_1intro.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.s16mvvmcleanachitecture.R
import com.example.s16mvvmcleanachitecture.databinding.FragmentArtDetailBinding
import com.example.test_1intro.model.util.Status
import com.example.test_1intro.viewmodel.ArtViewModel
import javax.inject.Inject

class ArtDetailsFragment @Inject constructor(private val glide: RequestManager) :
    Fragment(R.layout.fragment_art_detail) {
    private lateinit var binding: FragmentArtDetailBinding
    private lateinit var viewModel: ArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtDetailBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        binding.artimageView.setOnClickListener {
            findNavController().navigate(R.id.action_artDetailsFragment_to_imageApiFragment)
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        subscribeToObservers()

        binding.saveButton.setOnClickListener {
            viewModel.makeArt(
                binding.nameText.text.toString(),
                binding.artistText.text.toString(),
                binding.yearText.text.toString()
            )
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    private fun subscribeToObservers() {
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url ->
            binding?.let {
                glide.load(url).into(it.artimageView)
            }
        })
        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertArtMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message?: "Error", Toast.LENGTH_LONG).show()

                }

                Status.LOADING -> {

                }
            }
        })
    }
}