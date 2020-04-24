package com.belsoft.cosulbio.utils

import com.belsoft.cosulbio.MainActivity
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.database.DbRepository
import com.belsoft.cosulbio.services.IRequestHelper
import com.belsoft.cosulbio.services.RequestHelper
import com.belsoft.cosulbio.ui.login.LoginViewModel

object InjectorUtils {

    private val dbRepository: DbRepository = DbRepository.getInstance(MainActivity.appContext)
    private val requestHelper: IRequestHelper = RequestHelper.getInstance(MainActivity.appContext)

    fun provideMainViewModelFactory(): ViewModelFactory<MainViewModel> {
        return ViewModelFactory(MainViewModel(requestHelper, dbRepository))
    }

    fun provideLoginViewModelFactory(mainViewModel: MainViewModel): ViewModelFactory<LoginViewModel> {
        return ViewModelFactory(LoginViewModel(requestHelper, dbRepository, mainViewModel))
    }
}