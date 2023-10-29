package com.example.s08recycleviewfundamental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s16mvvmcleanachitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = listOf<Fruit>(
            Fruit("Khai"),
            Fruit("Hong")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter =
            RecycleViewAdapter(list) { itemSeleted: Fruit -> listItemClick(itemSeleted) }
    }

    private fun listItemClick(fruit: Fruit) {
        Toast.makeText(this, "this is ${fruit.name}", Toast.LENGTH_LONG).show()
    }
}