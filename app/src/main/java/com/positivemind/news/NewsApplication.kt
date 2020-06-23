package com.positivemind.news

import android.app.Application
import com.positivemind.news.di.ApplicationComponent
import com.positivemind.news.di.DaggerApplicationComponent

/**
 * Created by Rajeev Tomar on 21,June,2020
 */
class NewsApplication:Application() {

    lateinit var appComponent:ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }

}