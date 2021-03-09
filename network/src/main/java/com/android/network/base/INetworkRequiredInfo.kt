package com.android.network.base

import android.app.Application

interface INetworkRequiredInfo {

    fun getApplication(): Application

    fun getAppVersionName(): String

    fun getAppVersionCode(): String

    fun isDebug(): Boolean
}