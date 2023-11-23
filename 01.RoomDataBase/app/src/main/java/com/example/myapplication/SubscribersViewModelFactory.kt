package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SubscribersViewModelFactory(private val repository: SubscriberRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscribersViewModel::class.java)){
            return SubscribersViewModel(repository) as T
        }
        throw IllegalArgumentException("exception from SubscribersViewModel")
    }

}