package com.belsoft.cosulbio

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class MainViewModel(private val dbRepository : IDbRepository,
                    private val requestHelper : IRequestHelper,
                    private val appContext: Context) : BaseViewModel() {

    val singleLiveEvent = SingleLiveEvent<Unit>()
    val toastMessage = SingleLiveEvent<Int>()
    val toastMessageString = SingleLiveEvent<String>()

    val isFabButtonEnabled = MutableLiveData<Boolean>().apply { value = false }
}