package com.belsoft.cosulbio.models

data class LoginFormItemModel(
    val hint: String,
    var value: String = "",
    var isValidated: Boolean? = null
)