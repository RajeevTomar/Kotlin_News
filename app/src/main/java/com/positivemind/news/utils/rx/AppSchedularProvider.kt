package com.positivemind.news.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Rajeev Tomar on 22,June,2020
 */
class AppSchedularProvider:SchedulerProvider {

    override fun ui(): Scheduler? {
       return  AndroidSchedulers.mainThread()
    }

    override fun computation(): Scheduler? {
       return Schedulers.computation()
    }

    override fun io(): Scheduler? {
       return Schedulers.io()
    }
}