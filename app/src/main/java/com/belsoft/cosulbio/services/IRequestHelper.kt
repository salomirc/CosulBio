package com.belsoft.cosulbio.services

import android.content.Context
import com.belsoft.cosulbio.database.User

interface IRequestHelper {

    fun hasInternetConnection() : Boolean

    fun getFrequencies() : List<String>?

    fun login(username: String, password: String): User?
}