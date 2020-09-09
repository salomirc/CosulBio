package com.belsoft.cosulbio.services

import com.belsoft.cosulbio.database.User
import com.belsoft.cosulbio.models.Product

interface IRequestHelper {

    fun hasInternetConnection() : Boolean
    fun login(username: String, password: String): User?
//    fun getProducts(token: String) : List<Product>?
    fun getProducts() : List<Product>?
}