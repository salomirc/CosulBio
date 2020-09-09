package com.belsoft.cosulbio.ui.home

import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.models.Product
import com.belsoft.cosulbio.services.IRequestHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(private val _mainViewModel: MainViewModel,
       private val dbRepository : IDbRepository,
       private val requestHelper : IRequestHelper) : BaseViewModel() {

    suspend fun getAllProducts() {
        isVisibleProgressBar.value = true
        val products: List<Product>? = withContext(Dispatchers.IO){
            requestHelper.getProducts()
        }

        products?.let {
            _mainViewModel.allProducts.value = it
//            val id = withContext(Dispatchers.IO) {
//                dbRepository.addUser(products)
//            }
        }
        isVisibleProgressBar.value = false
    }
}