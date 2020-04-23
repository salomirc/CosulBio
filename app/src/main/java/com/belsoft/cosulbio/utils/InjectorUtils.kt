package com.belsoft.cosulbio.utils

import com.belsoft.cosulbio.MainActivity
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.services.IRequestHelper
import com.belsoft.cosulbio.services.RequestHelper

object InjectorUtils {
    private val requestHelper: IRequestHelper = RequestHelper.getInstance(MainActivity.appContext)

    fun provideMainViewModelFactory(): ViewModelFactory<MainViewModel> {
        return ViewModelFactory(MainViewModel(requestHelper))
    }
}