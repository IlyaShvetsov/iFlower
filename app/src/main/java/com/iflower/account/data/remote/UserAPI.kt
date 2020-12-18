package com.iflower.account.data.remote

import okhttp3.*



object UserAPI {
    private const val API_URL = "https://iflower-server.herokuapp.com"

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

}
