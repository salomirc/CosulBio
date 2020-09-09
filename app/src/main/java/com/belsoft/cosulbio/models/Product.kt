package com.belsoft.cosulbio.models

import java.math.BigDecimal

data class Product(
    val id: Int?,
    val name: String,
    val price: BigDecimal,
    val unit: String,
    val imageName: String,
    val isEnabled: Boolean,
    val details: String?
)