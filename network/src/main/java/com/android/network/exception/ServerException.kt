package com.android.network.exception

class ServerException(val code: Int, override val message: String) : RuntimeException()