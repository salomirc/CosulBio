package com.belsoft.cosulbio

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.database.User
import com.belsoft.cosulbio.models.Product
import com.belsoft.cosulbio.services.IRequestHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class MainViewModel(private val dbRepository : IDbRepository,
                    private val requestHelper : IRequestHelper) : BaseViewModel() {

    val singleLiveEvent = SingleLiveEvent<Unit>()
    val navigateLiveEvent = SingleLiveEvent<Int>()
    val toastMessage = SingleLiveEvent<Int>()
    val toastMessageString = SingleLiveEvent<String>()

    val isFabButtonEnabled = MutableLiveData<Boolean>().apply { value = false }
    val userInfo = MutableLiveData<User>()
    val isUserLogged: LiveData<Boolean> = Transformations.map(userInfo) { user ->
        user != null
    }

    val allProducts = MutableLiveData<List<Product>>().apply { value = listOf() }

    fun getUsersFromDb() {
        viewModelScope.launch {
            val users = withContext(Dispatchers.IO){
                dbRepository.getUsers()
            }
            if (users.isNotEmpty()){
                userInfo.value = users[0]
            }
        }
    }
}