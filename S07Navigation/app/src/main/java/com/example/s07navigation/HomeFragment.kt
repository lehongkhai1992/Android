package com.example.s07navigation

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.s16mvvmcleanachitecture.R
import com.example.s16mvvmcleanachitecture.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        bundle.putString("key", binding.editTextText.text.toString())
        binding.button.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editTextText.text.toString())) {
                val bundle = bundleOf("user_input" to binding.editTextText.text.toString())
                it.findNavController().navigate(R.id.action_homeFragment_to_secondragment, bundle)
            } else {
                Toast.makeText(activity, "Please insert your name", Toast.LENGTH_LONG).show()
            }
        }
    }

}