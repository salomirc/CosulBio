package com.belsoft.cosulbio.api

import com.belsoft.cosulbio.models.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {
//    @GET("getProducts")
//    fun getProducts(@Header("Authorization") token: String) : Call<List<Product>>

    @GET("getProducts")
    fun getProducts() : Call<List<Product>>

    @GET("getFile/{fileName}")
    fun getFileAsByteArray(@Path("fileName") fileName : String) : Call<ResponseBody>
}