package com.example.test_1intro.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.s16mvvmcleanachitecture.R
import com.example.test_1intro.view.ArtFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }
}