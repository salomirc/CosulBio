package com.belsoft.cosulbio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belsoft.cosulbio.components.SingleLiveEvent

open class BaseViewModel: ViewModel() {

    val toastMessage = SingleLiveEvent<Int>()
    val isVisibleSearchSelectProgessBar = MutableLiveData<Boolean>().apply { value = false }
}