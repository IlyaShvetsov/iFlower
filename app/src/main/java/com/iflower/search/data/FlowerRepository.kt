package com.iflower.search.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.iflower.search.data.remote.FlowerAPI
import com.iflower.search.data.model.Flower



class FlowerRepository {
    val allFlowers = MutableLiveData<List<Flower>>().apply {
        value = mutableListOf()
    }

    /** Возвращает false, если нет интернета (или возникла другая ошибка) */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateData(): Boolean {
        return try {
            val flowers = FlowerAPI.getFlowers()
            allFlowers.postValue(flowers)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
