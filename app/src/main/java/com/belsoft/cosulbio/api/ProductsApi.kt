package com.belsoft.cosulbio.api

import com.belsoft.cosulbio.models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductsApi {
//    @GET("getProducts")
//    fun getProducts(@Header("Authorization") token: String) : Call<List<Product>>

    @GET("getProducts")
    fun getProducts() : Call<List<Product>>
}