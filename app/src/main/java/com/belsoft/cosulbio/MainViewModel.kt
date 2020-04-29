package com.belsoft.cosulbio

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.belsoft.cosulbio.components.SingleLiveEvent

class MainViewModel(application: Application) : BaseViewModel(application) {

    val singleLiveEvent = SingleLiveEvent<Unit>()
    val toastMessage = SingleLiveEvent<Int>()
    val toastMessageString = SingleLiveEvent<String>()

    val isFabButtonEnabled = MutableLiveData<Boolean>().apply { value = false }
}