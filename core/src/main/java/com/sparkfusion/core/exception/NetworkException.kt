package com.sparkfusion.core.exception

class NetworkException(cause: Throwable) : MainException(cause.message, cause)