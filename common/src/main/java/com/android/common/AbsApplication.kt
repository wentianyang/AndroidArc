package com.android.common

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.android.base.activity.ActivityManager
import com.android.network.base.INetworkRequiredInfo
import com.android.network.base.NetworkApi

abstract class AbsApplication : Application() {

    private val _instance: Context by lazy {
        this.applicationContext
    }

    private val _activityLifecycleCallback = object : ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            ActivityManager.getInstance().add(activity)
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityDestroyed(activity: Activity) {
            ActivityManager.getInstance().remove()
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        NetworkApi.initNetwork(networkInfo())

        registerActivityLifecycleCallbacks(_activityLifecycleCallback)
    }

    abstract fun networkInfo(): INetworkRequiredInfo

    companion object {
        lateinit var instance: Application
    }
}