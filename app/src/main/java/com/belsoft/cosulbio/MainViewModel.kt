package com.belsoft.cosulbio

import androidx.lifecycle.ViewModel
import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class MainViewModel(val requestHelper: IRequestHelper, val dbRepository: IDbRepository) : ViewModel() {

    val toastMessage = SingleLiveEvent<Int?>()
    val singleLiveEvent = SingleLiveEvent<Unit>()

//    var dontKnowPumpsList = mutableListOf<DontKnowPumpItemModel>()

//    suspend fun requestData() {
//        if (requestHelper.hasInternetConnection()){
//            isVisibleSearchSelectProgessBar.value = true
//
//            val performanceDoc = selectedPump?.performanceDoc ?: ""
//
//            delay(3000)
//
//            val responseStatusCodeModel = withContext(Dispatchers.IO){
//                requestHelper.getPdfFile(performanceDoc)
//            }
//
//            if (responseStatusCodeModel != null && responseStatusCodeModel.statusCode == 200) {
//                pdfAsByteArrayLiveData.value = responseStatusCodeModel.responseValue
//            }
//            else if (responseStatusCodeModel != null && responseStatusCodeModel.statusCode == 404){
//                isVisibleSearchSelectProgessBar.value = false
//                isVisiblePdfView.value = false
//            }
//            else {
//                toastMessage.value = R.string.something_went_wrong
//            }
//
//            isVisibleSearchSelectProgessBar.value = false
//        }
//        else {
//            toastMessage.value = R.string.no_internet_connection
//            singleLiveEvent.call()
//        }
//    }

}