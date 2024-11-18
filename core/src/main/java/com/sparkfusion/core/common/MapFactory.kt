package com.sparkfusion.core.common

interface MapFactory<I, O> {
    suspend fun mapTo(input: I): O
    suspend fun mapFrom(input: O): I
}