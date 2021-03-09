package com.android.live

import com.android.live.bean.HomeBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("AppView/Index/New3")
    fun home(): Observable<HomeBean>
}