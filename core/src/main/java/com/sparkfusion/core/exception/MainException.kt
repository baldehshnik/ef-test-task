package com.sparkfusion.core.exception

open class MainException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message ?: "Default application exception", cause)