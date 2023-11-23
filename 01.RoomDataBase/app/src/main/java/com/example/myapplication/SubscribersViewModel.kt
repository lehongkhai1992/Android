package com.example.myapplication

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class SubscribersViewModel(private val repository: SubscriberRepository) : ViewModel() {

    private var isUpdateOrDelete = false
    private lateinit var subscribersUpdateOrDelete: Subscribers

    val subscribers = repository.subscribers

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    var saveOrUpdateButtonText = MutableLiveData<String>()
    var clearAllOrDeleteText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage


    init {
        saveOrUpdateButtonText.value = SAVE
        clearAllOrDeleteText.value = CLEAR_ALL
    }

    fun saveOrUpdate() {
        if (inputName.value == null || inputEmail.value == null) {
            statusMessage.value = Event("please correct name or email")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("please correct email")
        } else {
            if (isUpdateOrDelete) {
                subscribersUpdateOrDelete.name = inputName.value!!
                subscribersUpdateOrDelete.email = inputEmail.value!!
                update(subscribersUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                val subscribers = Subscribers(id = ID_SUBSCRIBER, name = name, email = email)
                insert(subscribers)
                inputName.value = EMPTY
                inputEmail.value = EMPTY
            }
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscribersUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    private fun insert(subscribers: Subscribers) =
        viewModelScope.launch(IO) {
            repository.insert(subscribers)
            withContext(Main) {
                statusMessage.value = Event("subscriber insert successfully")
            }
        }

    private fun update(subscribers: Subscribers) =
        viewModelScope.launch(IO) {
            repository.update(subscribers)
            withContext(Main) {
                statusMessage.value = Event("subscriber update successfully")
                resetValue()
            }
        }

    private fun delete(subscribers: Subscribers) {
        viewModelScope.launch(IO)
        {
            repository.delete(subscribers)
            withContext(Main) {
                resetValue()
                statusMessage.value = Event("subscriber delete successfully")
            }
        }
    }

    private fun clearAll() =
        viewModelScope.launch(IO) {
            repository.deleteAll()
            withContext(Main) {
                statusMessage.value = Event("subscriber delete all successfully")
            }
        }

    fun initUpdateOrDelete(subscribers: Subscribers) {
        inputName.value = subscribers.name
        inputEmail.value = subscribers.email
        isUpdateOrDelete = true
        subscribersUpdateOrDelete = subscribers
        saveOrUpdateButtonText.value = UPDATE
        clearAllOrDeleteText.value = DELETE
    }

    private fun resetValue() {
        inputName.value = EMPTY
        inputEmail.value = EMPTY
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = SAVE
        clearAllOrDeleteText.value = CLEAR_ALL
    }

    companion object {
        const val EMPTY = ""
        const val ID_SUBSCRIBER = 0
        const val SAVE = "Save"
        const val UPDATE = "Update"
        const val DELETE = "Delete"
        const val CLEAR_ALL = "Clear All"
    }
}