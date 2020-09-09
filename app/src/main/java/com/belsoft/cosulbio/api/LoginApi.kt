package com.belsoft.cosulbio.api

import com.belsoft.cosulbio.database.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface LoginApi {

    @GET("login")
    fun login(@Header("Authorization") authorization: String) : Call<User>
}