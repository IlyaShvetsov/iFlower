package com.iflower.account.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.iflower.account.data.UserRepository
import com.iflower.account.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class AccountModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountViewModel(context) as T
    }
}

class AccountViewModel(context: Context) : ViewModel() {
    private val repository = UserRepository(context)
    val loggedIn = repository.loggedIn
    val indicatorLogin = repository.indicatorLogin
    val indicatorRegister = repository.indicatorRegister

    /** Launching a new coroutine to insert the data in a non-blocking way */
    fun registration(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.registration(user)
    }

    /** Launching a new coroutine to insert the data in a non-blocking way */
    fun login(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.login(username, password)
    }

    /** Launching a new coroutine to insert the data in a non-blocking way */
    fun logout() = viewModelScope.launch(Dispatchers.IO) {
        repository.logout()
    }

}
