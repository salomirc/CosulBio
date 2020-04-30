package com.belsoft.cosulbio.utils

import android.content.Context
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.database.DbRepository
import com.belsoft.cosulbio.services.RequestHelper
import com.belsoft.cosulbio.ui.login.LoginViewModel

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

    fun provideLoginViewModelFactory(): ViewModelFactory<LoginViewModel> {
        return ViewModelFactory {
            LoginViewModel(dbRepository, requestHelper, appContext)
        }
    }
}