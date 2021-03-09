package com.android.network

import com.android.network.base.NetworkApi
import com.android.network.exception.ServerException
import com.android.network.model.BaseModel
import io.reactivex.rxjava3.functions.Function
import okhttp3.Interceptor

class GithubApi : NetworkApi() {

    override fun <T> getNetworkErrorHandler(): Function<T, T> {
        return Function<T, T> {
            if (it is BaseModel && it.resultCode != 0) {
                val exception = ServerException(it.resultCode, it.resultMessage)
                throw exception
            }
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
        private var instance: GithubApi? = null

        @JvmStatic
        fun getInstance(): GithubApi {
            return instance ?: synchronized(this) {
                instance ?: GithubApi().also {
                    instance = it
                }
            }
        }

        fun <T> createService(clazz: Class<T>): T {
            return getInstance().createService(clazz)
        }
    }
}