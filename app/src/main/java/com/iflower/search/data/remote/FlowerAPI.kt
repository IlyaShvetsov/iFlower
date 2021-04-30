package com.iflower.search.data.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iflower.search.data.model.Flower
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import java.lang.reflect.Type



object FlowerAPI {
    const val API_URL = "http://130.193.49.85:8090/iflower"

    fun getFlowers(): List<Flower> {
        val request = Request.Builder()
                .url("$API_URL/flowers/get_all")
                .build()
        val response = OkHttpClient().newCall(request).execute()
        val responseBody = response.body()!!.string()

        val listFlower: Type = object : TypeToken<List<Flower?>?>() {}.type
        return Gson().fromJson(responseBody, listFlower)
    }

}
