package com.belsoft.cosulbio

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.models.FormItemModel
import com.belsoft.cosulbio.models.LoginFormItemModel

open class BaseViewModel: ViewModel() {

    val toastMessage = SingleLiveEvent<Int>()
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