package com.android.network.rx

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver

class BaseSingle<T> : Single<T>() {
    /**
     * Implement this method in subclasses to handle the incoming [SingleObserver]s.
     *
     * There is no need to call any of the plugin hooks on the current `Single` instance or
     * the `SingleObserver`; all hooks and basic safeguards have been
     * applied by [.subscribe] before this method gets called.
     * @param observer the `SingleObserver` to handle, not `null`
     */
    override fun subscribeActual(observer: SingleObserver<in T>?) {
        TODO("Not yet implemented")
    }
}