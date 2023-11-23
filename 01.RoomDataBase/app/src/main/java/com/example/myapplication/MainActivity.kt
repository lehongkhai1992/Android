package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SubscribersViewModel
    private lateinit var adapter: RecycleViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao = SubscriberDatabase.getInstance(applicationContext)?.subscriberDao
        val repository: SubscriberRepository = SubscriberRepository(dao!!)
        val factory = SubscribersViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[SubscribersViewModel::class.java]
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
        initRecycleView()
        viewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Log.d(TAG, "wrap event $it")
            }
        })
    }


    private fun initRecycleView() {
        binding.subscriberRecycleView.layoutManager = LinearLayoutManager(this)
        displaySubscribersList()
        adapter = RecycleViewAdapter{ itemSelected: Subscribers ->
            listItemClick(itemSelected)
        }
        binding.subscriberRecycleView.adapter = adapter

    }

    private fun displaySubscribersList() {
        viewModel.subscribers.observe(this, Observer {
            Log.d(TAG, "$it")
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClick(subscribers: Subscribers) {
        Log.d(TAG, "the name ${subscribers.name} is selected")
        viewModel.initUpdateOrDelete(subscribers)
    }

    companion object {
        val TAG = "displaySubscribersList"
    }

}