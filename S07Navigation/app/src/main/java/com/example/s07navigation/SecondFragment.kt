package com.example.s07navigation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.s16mvvmcleanachitecture.R
import com.example.s16mvvmcleanachitecture.databinding.FragmentHomeBinding
import com.example.s16mvvmcleanachitecture.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        // Inflate the layout for this fragment
        val bundle = arguments!!.getString ("user_input", "12")
        Log.d("Second"," $bundle")
        binding.textView.text = bundle.toString()
        return binding.root
    }

}