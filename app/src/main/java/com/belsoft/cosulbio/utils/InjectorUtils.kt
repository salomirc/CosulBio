package com.belsoft.cosulbio.utils

import android.content.Context
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.database.DbRepository
import com.belsoft.cosulbio.services.RequestHelper
import com.belsoft.cosulbio.ui.login.LoginViewModel
import com.belsoft.cosulbio.ui.loginsucces.LoginSuccessViewModel
import com.belsoft.cosulbio.ui.logout.LogoutViewModel

class InjectorUtils private constructor(private val appContext: Context) {

    companion object {
        @Volatile
        private var instance: InjectorUtils? = null

        fun getInstance(appContext: Context): InjectorUtils {
            return instance ?: synchronized(this) {
                instance ?: InjectorUtils(appContext).also { instance = it }
            }
        }
    }

    private val dbRepository = DbRepository.getInstance(appContext)
    private val requestHelper = RequestHelper.getInstance(appContext)

    fun provideMainViewModelFactory(): ViewModelFactory<MainViewModel> {
        return ViewModelFactory {
            MainViewModel(dbRepository, requestHelper, appContext)
        }
    }

    fun provideLoginViewModelFactory(_mainViewModel: MainViewModel): ViewModelFactory<LoginViewModel> {
        return ViewModelFactory {
            LoginViewModel(_mainViewModel, dbRepository, requestHelper, appContext)
        }
    }

    fun provideLoginSuccessViewModelFactory(_mainViewModel: MainViewModel): ViewModelFactory<LoginSuccessViewModel> {
        return ViewModelFactory {
            LoginSuccessViewModel(_mainViewModel, dbRepository, requestHelper, appContext)
        }
    }

    fun provideLogoutViewModelFactory(_mainViewModel: MainViewModel): ViewModelFactory<LogoutViewModel> {
        return ViewModelFactory {
            LogoutViewModel(_mainViewModel, dbRepository, requestHelper, appContext)
        }
    }
}