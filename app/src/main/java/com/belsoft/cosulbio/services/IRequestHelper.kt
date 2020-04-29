package com.belsoft.cosulbio.services

import android.content.Context

interface IRequestHelper {

    fun hasInternetConnection() : Boolean

    fun getFrequencies() : List<String>?
}