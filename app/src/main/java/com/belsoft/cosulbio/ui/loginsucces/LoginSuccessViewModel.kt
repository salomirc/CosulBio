package com.belsoft.cosulbio.ui.loginsucces

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    fun navigateToHomeFragment(){
        viewModelScope.launch {
            try {
                delay(3000)
                _mainViewModel.navigateLiveEvent.value = (R.id.action_loginSuccessFragment_to_homeFragment)
            }
            catch (e: CancellationException) {
                println("Coroutine cancelled - ${e.message}")
            }
        }
    }
}