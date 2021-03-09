package com.android.network.interceptor

import com.android.base.utils.date.DateUtils
import com.android.network.base.INetworkRequiredInfo
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 通用的网络请求拦截器
 * 在网络请求前添加公共参数
 */
class CommonRequestInterceptor(private val networkRequiredInfo: INetworkRequiredInfo) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newChain = chain.request().newBuilder().apply {
            addHeader("os", "android")
            addHeader("appVersion", networkRequiredInfo.getAppVersionCode())
            addHeader("Source", "source")
            addHeader("Authorization", "")
            addHeader("Date", DateUtils.todayDate())
        }
        return chain.proceed(newChain.build())
    }
}