package com.android.live.bean

import com.google.gson.annotations.SerializedName

data class HomeBean(
    @SerializedName("BussinessCode") val bussinessCode: Int,
    @SerializedName("Succeed") val succeed: Boolean,
    @SerializedName("Msg") val ssg: String,
    @SerializedName("ResultCode") val resultCode: String,
    @SerializedName("Body") val body: BodyBean
)

data class BodyBean(
    @SerializedName("ActivityZone") val activityZone: ActivityZoneBean
)

data class ActivityZoneBean(
    @SerializedName("ActionUrl") val actionUrl: String,
    @SerializedName("Remark") val remark: String,
    @SerializedName("IsEnable") val isEnable: Boolean,
    @SerializedName("Type") val type: Int,
    @SerializedName("Index") val index: Int,
    @SerializedName("Title") val title: String,
    @SerializedName("Url") val url: String,
    @SerializedName("Summary") val summary: String,
    @SerializedName("Items") val items: List<ItemBean>
)

data class ItemBean(
    @SerializedName("ImgUrl") val imgUrl: String,
    @SerializedName("Url") val url: String,
    @SerializedName("WebUrl") val webUrl: String,
    @SerializedName("AppUrl") val appUrl: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Summary") val summary: String
)