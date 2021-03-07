package com.iflower.search.data.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iflower.search.data.model.Flower
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import java.lang.reflect.Type



object FlowerAPI {
    private const val API_URL = "https://iflower-server.herokuapp.com"

    fun getFlowers(): List<Flower> {
        val request = Request.Builder()
                .url("$API_URL/flowers/get_all")
                .build()
        val response = OkHttpClient().newCall(request).execute()
        val responseBody = response.body()!!.string()

        val listFlower: Type = object : TypeToken<List<Flower?>?>() {}.type
        return Gson().fromJson(responseBody, listFlower)
    }

    fun getImage(name: String): ResponseBody? {
        val url = "$API_URL/images/download?name=$name"
        val request = Request.Builder()
                .url(url)
                .build()
        val response = OkHttpClient().newCall(request).execute()
        return response.body()
    }

}
