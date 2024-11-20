package com.sparkfusion.core.converter

import android.os.Build
import android.text.Html

class HtmlConverter {

    fun convertHtmlToPlainText(htmlString: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(htmlString).toString()
        }
    }
}