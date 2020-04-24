package com.belsoft.cosulbio.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val requestHelper: IRequestHelper,
                     private val dbRepository: IDbRepository,
                     private val mainViewModel: MainViewModel) : BaseViewModel() {

    init {
        viewModelScope.launch {
            delay(500)
            mainViewModel.toastMessage.value = R.string.login
        }
    }

}
