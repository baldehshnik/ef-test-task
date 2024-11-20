package com.sparkfusion.core.converter

import java.text.SimpleDateFormat
import java.util.Locale

class DateConverter {

    fun convertDateString(dateString: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))

        return try {
            val date = inputFormat.parse(dateString)
            date?.let { outputFormat.format(it) }
        } catch (e: Exception) {
            null
        }
    }
}
