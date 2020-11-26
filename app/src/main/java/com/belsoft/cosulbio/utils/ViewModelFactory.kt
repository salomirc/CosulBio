package com.belsoft.cosulbio.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class ViewModelFactory<T : ViewModel?> constructor (private val factory: () -> T) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return factory() as T
    }
}