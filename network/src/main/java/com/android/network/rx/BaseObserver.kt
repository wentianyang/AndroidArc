package com.android.network.rx

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseObserver<T> : Observer<T> {
    /**
     * Provides the [Observer] with the means of cancelling (disposing) the
     * connection (channel) with the [Observable] in both
     * synchronous (from within [.onNext]) and asynchronous manner.
     * @param d the [Disposable] instance whose [Disposable.dispose] can
     * be called anytime to cancel the connection
     * @since 2.0
     */
    override fun onSubscribe(d: Disposable?) {
    }

    /**
     * Provides the [Observer] with a new item to observe.
     *
     *
     * The [Observable] may call this method 0 or more times.
     *
     *
     * The `Observable` will not call this method again after it calls either [.onComplete] or
     * [.onError].
     *
     * @param t
     * the item emitted by the Observable
     */
    override fun onNext(data: T) {
        this.onSuccess(data)
    }

    /**
     * Notifies the [Observer] that the [Observable] has experienced an error condition.
     *
     *
     * If the `Observable` calls this method, it will not thereafter call [.onNext] or
     * [.onComplete].
     *
     * @param e
     * the exception encountered by the Observable
     */
    override fun onError(e: Throwable?) {
        this.onFailure(e)
    }

    /**
     * Notifies the [Observer] that the [Observable] has finished sending push-based notifications.
     *
     *
     * The `Observable` will not call this method if it calls [.onError].
     */
    override fun onComplete() {
    }

    abstract fun onSuccess(data: T)

    abstract fun onFailure(e: Throwable?)
}