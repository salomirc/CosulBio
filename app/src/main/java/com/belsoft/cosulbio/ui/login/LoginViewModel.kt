package com.belsoft.cosulbio.ui.login

import androidx.lifecycle.ViewModel
import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class LoginViewModel(val requestHelper: IRequestHelper, val dbRepository: IDbRepository) : BaseViewModel() {

    fun displayToast(resId: Int){
        mainViewModel.toastMessage.value = resId
    }

}
