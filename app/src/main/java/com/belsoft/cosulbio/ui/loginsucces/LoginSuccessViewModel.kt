package com.belsoft.cosulbio.ui.loginsucces

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class LoginSuccessViewModel(private val _mainViewModel: MainViewModel,
                            private val dbRepository : IDbRepository,
                            private val requestHelper : IRequestHelper,
                            private val appContext: Context) : BaseViewModel() {

    val userFullName: LiveData<String> = Transformations.map(_mainViewModel.userInfo) { user ->
        if (user != null){
            "${user.firstName} ${user.lastName}"
        }
        else {
            ""
        }
    }
}