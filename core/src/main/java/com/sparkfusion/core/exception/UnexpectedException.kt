package com.sparkfusion.core.exception

class UnexpectedException(
    cause: Throwable? = null
) : MainException(cause?.message ?: "Occurred unknown exception", cause)