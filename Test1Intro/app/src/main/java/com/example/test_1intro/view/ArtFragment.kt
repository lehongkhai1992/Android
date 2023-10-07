package com.example.test_1intro.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s16mvvmcleanachitecture.R
import com.example.s16mvvmcleanachitecture.databinding.FragmentArtsBinding
import com.example.test_1intro.Adapter.ArtRecyclerAdapter
import com.example.test_1intro.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class ArtFragment @Inject constructor(
    val artRecyclerAdapter: ArtRecyclerAdapter
) : Fragment(R.layout.fragment_arts) {

    //private val viewModel: ArtViewModel by viewModels()
    //private val viewModel: ArtViewModel by activityViewModels()
    lateinit var viewModel : ArtViewModel

    private var fragmentBinding : FragmentArtsBinding? = null
    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedArt = artRecyclerAdapter.arts[layoutPosition]
            viewModel.deleteArt(selectedArt)

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binding = FragmentArtsBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()

        binding.recyclerView.adapter = artRecyclerAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerView)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(
                ArtFragmentDirections.actionArtFragmentToArtDetailsFragment()
            )
        }

    }

    private fun subscribeToObservers() {
        viewModel.artList.observe(viewLifecycleOwner, Observer {
            artRecyclerAdapter.arts = it
        })
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }


}
