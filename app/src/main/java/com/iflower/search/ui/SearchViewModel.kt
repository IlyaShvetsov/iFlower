package com.iflower.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iflower.search.data.Repository
import com.iflower.search.data.model.Flower
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class SearchViewModel : ViewModel() {
    private val repository = Repository()
    val allFlowers: LiveData<List<Flower>> = repository.allFlowers
    val showError = MutableLiveData<Boolean>().apply {
        value = false
    }

    /** Launching a new coroutine to insert the data in a non-blocking way */
    fun updateData() = viewModelScope.launch(Dispatchers.IO) {
        showError.postValue(!repository.updateData())
    }

}
