package com.github.kongpf8848.animation.utils

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object RxJavaUtils {

    fun <T> run(onRxAndroidListener: OnRxAndroidListener<T>) {
        Observable.create(ObservableOnSubscribe { e: ObservableEmitter<T> ->
            try {
                val t: T? = onRxAndroidListener.doInBackground()
                if (t != null) {
                    e.onNext(t)
                } else {
                    e.onError(NullPointerException("on doInBackground result not null"))
                }
            } catch (throwable: Throwable) {
                e.onError(throwable)
            }
        } as ObservableOnSubscribe<T>)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .safeSubscribe(object : Observer<T> {
                private var d: Disposable? = null

                @Synchronized
                private fun dispose() {
                    d?.apply {
                        if(!isDisposed){
                            dispose()
                            d = null
                        }
                    }
                }
                override fun onSubscribe(d: Disposable) {
                    this.d = d
                }

                override fun onNext(result: T & Any) {
                    onRxAndroidListener.onSuccess(result)
                    dispose()
                    onComplete()
                }

                override fun onError(e: Throwable) {
                    onRxAndroidListener.onError(e)
                    dispose()
                    onComplete()
                }

                override fun onComplete() {
                    onRxAndroidListener.onComplete()
                }
            })
    }

    abstract class OnRxAndroidListener<T> {

        //在子线程执行
        @Throws(Throwable::class)
        abstract fun doInBackground(): T

        //事件执行成功, 在主线程回调
        abstract fun onSuccess(result: T)

        //事件执行失败, 在主线程回调
        abstract fun onError(e: Throwable?)

        open fun onComplete() {}
    }

}

