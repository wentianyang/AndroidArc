package com.android.network.exception

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function

class HttpErrorHandler<T> : Function<Throwable, Observable<T>> {
    /**
     * Apply some calculation to the input value and return some other value.
     * @param t the input value
     * @return the output value
     * @throws Throwable if the implementation wishes to throw any type of exception
     */
    override fun apply(t: Throwable?): Observable<T> {
        return Observable.error(ExceptionHandler.handlerException(t))
    }
}