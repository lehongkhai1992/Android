package com.example.test_1intro.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.test_1intro.adapter.ArtRecyclerAdapter
import com.example.test_1intro.adapter.ImageRecyclerAdapter
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val glide : RequestManager,
    private val artRecyclerAdapter: ArtRecyclerAdapter
) : FragmentFactory() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}