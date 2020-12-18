package com.iflower.account.data.local

import android.content.Context
import androidx.lifecycle.MutableLiveData



object UserStorage {

//    val preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//    val editor = preferences.edit()
//    val lastNumber = preferences.getInt("lastNumber", 0) + 1
//    editor.putInt("lastNumber", lastNumber)
//    val fileName = context.filesDir.absolutePath + "/record_$lastNumber.mp3"
//    editor.putString(fileName, getCurrentTime())
//    editor.apply()


    var loggedIn = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun logIn() {
        loggedIn.postValue(true)
    }

    fun logOut() {
        loggedIn.postValue(false)
    }

}
