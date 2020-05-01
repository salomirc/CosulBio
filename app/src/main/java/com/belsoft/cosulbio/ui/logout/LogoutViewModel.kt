package com.belsoft.cosulbio.ui.logout

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class LogoutViewModel(private val _mainViewModel: MainViewModel,
                      private val dbRepository : IDbRepository,
                      private val requestHelper : IRequestHelper,
                      private val appContext: Context) : BaseViewModel() {

    val isLogoutButtonEnabled: LiveData<Boolean> = Transformations.map(_mainViewModel.userInfo) { user ->
        user != null
    }

    fun onLogoutButtonClick() {
        _mainViewModel.userInfo.value = null
        _mainViewModel.navigateLiveEvent.value = R.id.action_logoutFragment_to_homeFragment
    }

}
