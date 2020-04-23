package com.belsoft.cosulbio.api

import retrofit2.Call
import retrofit2.http.GET

interface IDontKnowMyPumpApi {

    @GET("idontknowmypump/frequencies")
    fun getFrequencies() : Call<List<String>>
}