package com.iflower.account.data.local

import android.content.Context
import androidx.lifecycle.MutableLiveData



class UserStorage(private val context: Context) {
    companion object {
        const val prefName = "userStoragePreferences"
    }

    var loggedIn = MutableLiveData<Boolean>().apply {
        value = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).getBoolean("loggedIn", false)
    }

    fun logIn(userName: String) {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                .edit().putBoolean("loggedIn", true).putString("username", userName).apply()
        loggedIn.postValue(true)
    }

    fun logOut() {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                .edit().putBoolean("loggedIn", false).putString("username", "").apply()
        loggedIn.postValue(false)
    }

    fun getUsername(): String {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE).getString("username", "") ?: ""
    }

}
