package com.example.coinapplication

import java.text.DecimalFormat

object TextUtils {

    fun formatPrice(price: Double?): String {
        val decimalFormat = DecimalFormat("#,###.####")
        return "$${decimalFormat.format(price)}"
    }
}