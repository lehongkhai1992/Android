package com.example.s2_obtainviewsize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.ViewTreeObserver
import android.widget.TextView
import com.example.s16mvvmcleanachitecture.R
import com.example.s16mvvmcleanachitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTextSmall()

        binding.button.setOnClickListener {
            binding.txtSmall.text = binding.txtSmall.textSize.toString()
        }
        val viewTreeObserver = binding.txtSmall.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (binding.txtSmall.textSize < 34){
                    binding.txtMedium.textSize = 30f
                    binding.txtLarger.textSize = 40f

                }else if (binding.txtSmall.textSize > 34){
                    binding.txtMedium.textSize = 60f
                    binding.txtLarger.textSize = 80f
                }
                // Bạn nên loại bỏ listener sau khi sử dụng để tránh rò rỉ bộ nhớ
                binding.txtSmall.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun setTextSmall() {
        if (binding.txtSmall.textSize < 34){
            binding.txtMedium.textSize = 30f
            binding.txtLarger.textSize = 40f

        }else if (binding.txtSmall.textSize > 34){
            binding.txtMedium.textSize = 60f
            binding.txtLarger.textSize = 80f

        }
    }
}