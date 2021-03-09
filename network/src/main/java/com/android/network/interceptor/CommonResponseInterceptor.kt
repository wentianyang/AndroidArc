package com.android.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 通用的网络返回拦截器
 * 在网络请求完毕调用
 */
class CommonResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // 获取网络请求之前的时间
        val requestTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        // 网络请求结束后计算时间差
        Log.d(TAG, "intercept: request time--------> ${System.currentTimeMillis() - requestTime}")
        return response
    }

    companion object {
        const val TAG = "ResponseInterceptor"
    }
}