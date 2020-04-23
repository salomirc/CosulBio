package com.belsoft.cosulbio.services

import android.content.Context

interface IRequestHelper {

    fun hasInternetConnection(context: Context? = null) : Boolean

    fun getFrequencies() : List<String>?
}