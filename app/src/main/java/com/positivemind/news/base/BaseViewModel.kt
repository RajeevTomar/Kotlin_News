package com.positivemind.news.base

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import com.positivemind.news.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Rajeev Tomar on 09,June,2020
 */
open class BaseViewModel : BaseObservable {

    private var mCompositDisposable: CompositeDisposable
    private var mSchedulerProvider: SchedulerProvider
    private var mIsLoading: ObservableBoolean = ObservableBoolean(false)

    public constructor(schedulerProvider: SchedulerProvider) {
        mSchedulerProvider = schedulerProvider
        mCompositDisposable = CompositeDisposable()
    }

    protected var isLoading: ObservableBoolean
        get() = this.mIsLoading
        set(value) = mIsLoading.set(value.get())

    protected val schedularProvider: SchedulerProvider
        get() = this.mSchedulerProvider

    protected val compositeDisposable: CompositeDisposable
        get() = this.mCompositDisposable

}