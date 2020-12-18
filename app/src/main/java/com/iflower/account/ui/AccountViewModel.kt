package com.iflower.account.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iflower.account.data.UserRepository
import com.iflower.account.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class AccountViewModel : ViewModel() {
    private val repository = UserRepository()
    val loggedIn = repository.loggedIn
    val indicator = repository.indicator

    /** Launching a new coroutine to insert the data in a non-blocking way */
    fun login(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.login(username, password)
    }

    /** Launching a new coroutine to insert the data in a non-blocking way */
    fun registration(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.registration(user)
    }

}
