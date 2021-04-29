package com.iflower.account.data

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.iflower.account.data.local.UserStorage
import com.iflower.account.data.model.User
import com.iflower.account.data.remote.UserAPI



class UserRepository(context: Context) {
    private val userStorage = UserStorage(context)
    val loggedIn = userStorage.loggedIn
    val indicatorLogin = MutableLiveData<Int>().apply {
        value = 0
    }
    val indicatorRegister = MutableLiveData<Int>().apply {
        value = 0
    }

    /** Возвращает false, если нет интернета (или возникла другая ошибка) */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun registration(user: User): Boolean {
        return try {
            val registrationIsSuccess = UserAPI.registration(user)
            if (registrationIsSuccess) {
                userStorage.logIn()
            } else {
                indicatorRegister.postValue(indicatorRegister.value?.plus(1))
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /** Возвращает false, если нет интернета (или возникла другая ошибка) */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun login(username: String, password: String): Boolean {
        return try {
            val loginIsSuccess = UserAPI.login(username, password)
            if (loginIsSuccess) {
                userStorage.logIn()
            } else {
                indicatorLogin.postValue(indicatorLogin.value?.plus(1))
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun logout() {
        userStorage.logOut()
    }

}
