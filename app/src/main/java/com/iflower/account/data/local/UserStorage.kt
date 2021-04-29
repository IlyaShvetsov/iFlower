package com.iflower.account.data.local

import android.content.Context
import androidx.lifecycle.MutableLiveData



class UserStorage(private val context: Context) {
    companion object {
        private const val prefName = "userStoragePreferences"
    }

    var loggedIn = MutableLiveData<Boolean>().apply {
        val preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        value = preferences.getBoolean("loggedIn", false)
    }

    fun logIn() {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                .edit().putBoolean("loggedIn", true).apply()
        loggedIn.postValue(true)
    }

    fun logOut() {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                .edit().putBoolean("loggedIn", false).apply()
        loggedIn.postValue(false)
    }

}
