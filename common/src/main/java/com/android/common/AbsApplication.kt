package com.android.common

import android.app.Application
import android.content.Context
import com.android.network.base.INetworkRequiredInfo
import com.android.network.base.NetworkApi

abstract class AbsApplication : Application() {

    private val _instance: Context by lazy {
        this.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        NetworkApi.initNetwork(networkInfo())
    }

    abstract fun networkInfo(): INetworkRequiredInfo

    companion object {
        lateinit var instance: Application
    }
}