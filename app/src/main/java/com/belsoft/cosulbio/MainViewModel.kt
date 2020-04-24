package com.belsoft.cosulbio

import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class MainViewModel(val dbRepository: IDbRepository, val requestHelper: IRequestHelper) : BaseViewModel() {

    val singleLiveEvent = SingleLiveEvent<Unit>()

}