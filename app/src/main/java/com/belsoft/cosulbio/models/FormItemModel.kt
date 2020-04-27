package com.belsoft.cosulbio.models

import android.view.inputmethod.EditorInfo

data class FormItemModel(
    val hint: Int,
    var value: String = "",
    val inputType: Int,
    val imeOptions: Int = EditorInfo.IME_ACTION_NEXT,
    var isValidated: Boolean? = null
)