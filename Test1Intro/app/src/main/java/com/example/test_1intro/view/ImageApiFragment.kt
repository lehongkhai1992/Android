package com.example.test_1intro.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s16mvvmcleanachitecture.R
import com.example.s16mvvmcleanachitecture.databinding.FragmentImageApiBinding
import com.example.test_1intro.Adapter.ImageRecyclerAdapter
import com.example.test_1intro.model.util.Status
import com.example.test_1intro.viewmodel.ArtViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageApiFragment @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerAdapter
):Fragment(R.layout.fragment_image_api) {
    private lateinit var viewModel: ArtViewModel
    private lateinit var binding: FragmentImageApiBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)
        binding = FragmentImageApiBinding.bind(view)
        var job : Job? = null

        subscriberToObservers()

        binding.searchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    viewModel.searchForImage(it.toString())
                }
            }
        }

        binding.imageRecycleView.adapter = imageRecyclerAdapter
        binding.imageRecycleView.layoutManager = GridLayoutManager(requireContext(), 3)
        imageRecyclerAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
    }

    fun subscriberToObservers(){
        viewModel.imagesList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map {imageResult->
                        imageResult.previewURL
                    }
                    imageRecyclerAdapter.images = urls?: listOf()
                    binding?.progressBar?.visibility  = View.GONE
                }
                Status.LOADING -> {
                    Toast.makeText(requireContext(), it.message?:"Error", Toast.LENGTH_LONG).show()
                    binding?.progressBar?.visibility  = View.VISIBLE
                }
                Status.ERROR -> {
                    binding?.progressBar?.visibility  = View.VISIBLE
                }
            }
        })
    }
}