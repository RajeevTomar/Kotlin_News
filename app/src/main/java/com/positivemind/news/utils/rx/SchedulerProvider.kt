package com.positivemind.news.utils.rx

import io.reactivex.Scheduler

/**
 * Created by Rajeev Tomar on 09,June,2020
 */
interface SchedulerProvider {

    fun ui(): Scheduler?

    fun computation(): Scheduler?

    fun io(): Scheduler?
}