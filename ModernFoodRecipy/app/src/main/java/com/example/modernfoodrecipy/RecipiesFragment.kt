package com.example.modernfoodrecipy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.s16mvvmcleanachitecture.R

/**
 * A simple [Fragment] subclass.
 * Use the [RecipiesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipiesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipies, container, false)
    }
}