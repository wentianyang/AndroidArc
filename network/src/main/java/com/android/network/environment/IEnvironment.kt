package com.android.network.environment

interface IEnvironment {

    /**
     * 正式环境
     */
    fun release(): String

    /**
     * 测试环境
     */
    fun dev(): String
}