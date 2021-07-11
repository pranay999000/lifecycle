package com.example.livedata.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livedata.model.server.Dictionary
import com.example.livedata.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    var dictionary: MutableLiveData<Response<Dictionary>> = MutableLiveData()

    fun getDictionary() {
        viewModelScope.launch {
            val response: Response<Dictionary> = repository.getDictionary()
            dictionary.value = response
        }
    }
}