package com.belsoft.cosulbio.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.database.User
import com.belsoft.cosulbio.models.LoginFormItemModel
import com.belsoft.cosulbio.services.IRequestHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val dbRepository: IDbRepository,
    private val requestHelper: IRequestHelper) : BaseViewModel() {

    init {
        viewModelScope.launch {
//            addUserToDb()
        }
    }

    private suspend fun addUserToDb() {
        val user = User(
            "Ciprian",
            "redhatslx",
            "Ciprian",
            "Salomir",
            "ciprian.salomir@gmail.com",
            false
        )
        val users = withContext(Dispatchers.IO) {
            dbRepository.addUser(user)
            dbRepository.getUsers()
        }
        users.let {
            userInfo.value = it.toString()
            println()
        }
        withContext(Dispatchers.IO) {
             dbRepository.deleteAllUsers()
        }
    }


    val hideKeyboardSafeLiveEvent = SingleLiveEvent<Unit>()

    val userInfo = MutableLiveData<String>()
    val isLoginButtonEnabled = MutableLiveData<Boolean>().apply { value = false }

    var loginList = listOf<LoginFormItemModel>()
    val logins = MutableLiveData<List<LoginFormItemModel>>().apply { value = loginList }

    fun validateLoginField(text: String, index: Int){
        loginList[index].isValidated = isNotBlankValidation(text)
        isLoginButtonEnabled.value = allFieldsAreValidated(loginList)
    }

    fun onLoginButtonClick(){
        hideKeyboardSafeLiveEvent.call()
    }

    suspend fun onLoginButtonClickContinuation() {
        isLoginButtonEnabled.value = false
        isVisibleSearchSelectProgessBar.value = true
        val map = mutableMapOf<String, String>()
        for (item in loginList) {
            map[item.hint] = item.value
        }
        delay(5000)
        isVisibleSearchSelectProgessBar.value = false
        isLoginButtonEnabled.value = true
    }
}
