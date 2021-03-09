package com.android.androidarc

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("Artworks")
    fun login(): Observable<String>
}