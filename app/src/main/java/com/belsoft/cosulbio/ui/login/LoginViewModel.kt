package com.belsoft.cosulbio.ui.login

import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class LoginViewModel(val dbRepository: IDbRepository,
                     val requestHelper: IRequestHelper) : BaseViewModel() {

    init {
            toastMessage.value = R.string.login
        }

}
