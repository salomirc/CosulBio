package com.belsoft.cosulbio

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.database.User
import com.belsoft.cosulbio.services.IRequestHelper

class MainViewModel(private val dbRepository : IDbRepository,
                    private val requestHelper : IRequestHelper,
                    private val appContext: Context) : BaseViewModel() {

    val singleLiveEvent = SingleLiveEvent<Unit>()
    val navigateLiveEvent = SingleLiveEvent<Int>()
    val toastMessage = SingleLiveEvent<Int>()
    val toastMessageString = SingleLiveEvent<String>()

    val isFabButtonEnabled = MutableLiveData<Boolean>().apply { value = false }
    val userInfo = MutableLiveData<User>()
    val isUserLogged: LiveData<Boolean> = Transformations.map(userInfo) {user ->
        user != null
    }
}