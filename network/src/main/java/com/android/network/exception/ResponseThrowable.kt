package com.android.network.exception

data class ResponseThrowable(
    val t: Throwable,
    val code: Int,
    override val message: String
) : Throwable(message, t)