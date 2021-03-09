package com.android.androidarc

import android.app.Application
import com.android.common.AbsApplication
import com.android.network.base.INetworkRequiredInfo

class ArcNetwork : INetworkRequiredInfo {

    override fun getApplication(): Application {
        return AbsApplication.instance
    }

    override fun getAppVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun getAppVersionCode(): String {
        return BuildConfig.VERSION_CODE.toString()
    }

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}