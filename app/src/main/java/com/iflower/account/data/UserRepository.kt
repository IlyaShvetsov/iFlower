package com.iflower.account.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.iflower.account.data.local.UserStorage
import com.iflower.account.data.model.User
import com.iflower.account.data.remote.UserAPI



class UserRepository {
    val loggedIn = UserStorage.loggedIn
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

    /** Возвращает false, если нет интернета (или возникла другая ошибка) */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun registration(user: User): Boolean {
        return try {
            val registrationIsSuccess = UserAPI.registration(user)
            if (registrationIsSuccess) {
                UserStorage.logIn()
            } // else {
//                indicator.postValue(indicator.value?.plus(1))
//            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}
