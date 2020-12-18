package com.iflower.account.data.local



object UserStorage {
    var loggedIn = false

    fun logIn() {
        loggedIn = true
    }

    fun logOut() {
        loggedIn = false
    }

    fun checkLogIn(): Boolean {
        return loggedIn
    }

}
