package com.android.network

import com.android.network.base.NetworkApi
import io.reactivex.rxjava3.functions.Function
import okhttp3.Interceptor

class YwArtApi : NetworkApi() {

    override fun <T> getNetworkErrorHandler(): Function<T, T> {
        return Function {
            it
        }
    }

    override fun getInterceptor(): Interceptor? {
        return null
    }

    /**
     * 正式环境
     */
    override fun release(): String = ""

    /**
     * 测试环境
     */
    override fun dev(): String = ""

    companion object {

        private var INSTANCE: YwArtApi? = null

        @JvmStatic
        fun getInstance(): YwArtApi {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: YwArtApi().also { INSTANCE = it }
            }
        }

        fun <T> createService(clazz: Class<T>): T {
            return getInstance().createService(clazz)
        }
    }
}