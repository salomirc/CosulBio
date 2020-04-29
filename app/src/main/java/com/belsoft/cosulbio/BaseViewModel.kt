package com.belsoft.cosulbio

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.DbRepository
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.models.LoginFormItemModel
import com.belsoft.cosulbio.services.IRequestHelper
import com.belsoft.cosulbio.services.RequestHelper

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val dbRepository : IDbRepository = DbRepository.getInstance(application)
    val requestHelper : IRequestHelper = RequestHelper.getInstance(application)

    lateinit var mainViewModel: MainViewModel

    val isVisibleSearchSelectProgessBar = MutableLiveData<Boolean>().apply { value = false}

    protected fun isNotBlankValidation(text: String): Boolean {
        return text.trim().isNotBlank()
    }

    protected fun isEmailAddressValidation(text: String): Boolean {
        return (text.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(text).matches())
    }

    protected fun allFieldsAreValidated(items: List<LoginFormItemModel>): Boolean{
        for (item in items){
            if (item.isValidated == null || item.isValidated == false){
                return false
            }
        }
        return true
    }
}