package com.belsoft.cosulbio.ui.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.belsoft.cosulbio.BaseViewModel
import com.belsoft.cosulbio.MainViewModel
import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.models.Product
import com.belsoft.cosulbio.services.IRequestHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(private val _mainViewModel: MainViewModel,
       private val dbRepository : IDbRepository,
       private val requestHelper : IRequestHelper) : BaseViewModel() {

    val updateCellLiveEvent = SingleLiveEvent<Int>()

    private fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    suspend fun getAllProducts() {
        isVisibleProgressBar.value = true
        val products: List<Product>? = withContext(Dispatchers.IO){
            requestHelper.getProducts()
        }
        products?.let {
            _mainViewModel.allProducts.value = it
            isVisibleProgressBar.value = false
            for (index in it.indices) {
                it[index].bitmap = withContext(Dispatchers.IO){
                    val bytes = requestHelper.getFileAsByteArray(it[index].imageName)
                    return@withContext bytes?.let {
                        byteArrayToBitmap(bytes)
                    }
                }
                updateCellLiveEvent.value = index
            }

//            val id = withContext(Dispatchers.IO) {
//                dbRepository.addUser(products)
//            }
        }
//        isVisibleProgressBar.value = false
    }
}