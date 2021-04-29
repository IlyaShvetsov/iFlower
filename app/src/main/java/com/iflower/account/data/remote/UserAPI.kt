package com.iflower.account.data.remote

import com.iflower.account.data.model.User
import okhttp3.*



object UserAPI {
    private const val API_URL = "http://130.193.49.85:8090/iflower"

    fun login(username: String, password: String): Boolean {

        val requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                "{\"username\": \"$username\", \"password\": \"$password\"}"
        )

        val request = Request.Builder()
                .url("$API_URL/users/login")
                .post(requestBody)
                .build()

        val response = OkHttpClient().newCall(request).execute()
        return response.code() == 200
    }

    fun registration(user: User): Boolean {

        val username = user.userName
        val password = user.password

        val requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                "{\"username\": \"$username\", \"password\": \"$password\"}"
        )

        val request = Request.Builder()
                .url("$API_URL/users/add")
                .post(requestBody)
                .build()

        val response = OkHttpClient().newCall(request).execute()
        return response.code() == 200
    }

}
