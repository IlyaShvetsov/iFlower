package com.iflower.account.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.iflower.account.data.local.UserStorage
import com.iflower.account.data.remote.UserAPI
import com.iflower.search.data.remote.FlowerAPI
import com.iflower.search.data.model.Flower



class UserRepository {
    val loggedIn = MutableLiveData<Boolean>().apply {
        value = UserStorage.checkLogIn()
    }
    val indicator = MutableLiveData<Int>().apply {
        value = 0
    }

    /** Возвращает false, если нет интернета (или возникла другая ошибка) */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun login(username: String, password: String): Boolean {
        return try {
            val loginIsSuccess = UserAPI.login(username, password)
            if (loginIsSuccess) {
                loggedIn.postValue(true)
                UserStorage.logIn()
            } else {
                indicator.postValue(indicator.value?.plus(1))
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
