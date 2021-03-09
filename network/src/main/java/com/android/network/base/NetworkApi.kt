package com.android.network.base

import android.util.Log
import com.android.network.environment.EnvironmentSettingsActivity
import com.android.network.environment.IEnvironment
import com.android.network.exception.HttpErrorHandler
import com.android.network.interceptor.CommonRequestInterceptor
import com.android.network.interceptor.CommonResponseInterceptor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class NetworkApi : IEnvironment {

    // 通过baseUrl缓存Retrofit对象
    private val _baseUrlMap: HashMap<String, Retrofit> = HashMap()
    private val _baseUrl: String
        get() {
            return if (EnvironmentSettingsActivity.isDevEnvironment(iNetworkRequiredInfo.getApplication())
            ) {
                dev()
            } else {
                release()
            }
        }

    /**
     * 创建Retrofit对象
     */
    private fun <T> createRetrofit(clazz: Class<T>): Retrofit {
        Log.d("NetworkApi", "createRetrofit: base url is $_baseUrl")
        return _baseUrlMap[_baseUrl + clazz.name]
            ?: Retrofit
                .Builder()
                .baseUrl(_baseUrl)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().apply { _baseUrlMap[_baseUrl + clazz.name] = this }
    }

    protected fun <T> createService(service: Class<T>): T {
        return createRetrofit(service).create(service)
    }

    /**
     * 创建OkHttp
     */
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            getInterceptor()?.let {
                addInterceptor(it)
            }
            // 添加返回拦截器，加在最前面更准确?
            addInterceptor(CommonResponseInterceptor())
            // 添加通用请求拦截器
            addInterceptor(CommonRequestInterceptor(iNetworkRequiredInfo))
            if (iNetworkRequiredInfo.isDebug()) {
                // Debug模式下打印网络日志
                addInterceptor(createLoggingInterceptor())
            }
        }.build()
    }

    private fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun <T> applyScheduler(observer: Observer<T>): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(getNetworkErrorHandler<T>())
                .onErrorResumeNext(HttpErrorHandler<T>())
                .subscribe(observer)
            it
        }
    }

    abstract fun <T> getNetworkErrorHandler(): Function<T, T>

    abstract fun getInterceptor(): Interceptor?

    companion object {
        private lateinit var iNetworkRequiredInfo: INetworkRequiredInfo

        /**
         * 网络初始化
         * 需要在业务模块中实现 INetworkRequiredInfo 接口
         */
        @JvmStatic
        fun initNetwork(networkRequiredInfo: INetworkRequiredInfo) {
            iNetworkRequiredInfo = networkRequiredInfo;
        }
    }
}