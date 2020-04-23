package com.belsoft.cosulbio

import androidx.lifecycle.ViewModel
import com.belsoft.cosulbio.components.SingleLiveEvent

open class BaseViewModel: ViewModel() {

    lateinit var mainViewModel: MainViewModel

    val toastMessage = SingleLiveEvent<Int?>()
    val singleLiveEvent = SingleLiveEvent<Unit>()
}