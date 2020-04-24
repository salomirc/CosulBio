package com.belsoft.cosulbio

import com.belsoft.cosulbio.components.SingleLiveEvent
import com.belsoft.cosulbio.database.IDbRepository
import com.belsoft.cosulbio.services.IRequestHelper

class MainViewModel(
    private val dbRepository: IDbRepository,
    private val requestHelper: IRequestHelper) : BaseViewModel() {

    val singleLiveEvent = SingleLiveEvent<Unit>()

}