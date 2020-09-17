package com.belsoft.cosulbio.models

import android.graphics.Bitmap
import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val price: BigDecimal,
    val unit: String,
    val imageName: String,
    var bitmap: Bitmap?,
    val isEnabled: Boolean,
    val details: String?
)