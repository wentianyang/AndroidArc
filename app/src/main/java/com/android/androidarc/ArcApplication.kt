package com.android.androidarc

import com.android.common.AbsApplication
import com.android.network.base.INetworkRequiredInfo

class ArcApplication : AbsApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun networkInfo(): INetworkRequiredInfo {
        return ArcNetwork()
    }
}