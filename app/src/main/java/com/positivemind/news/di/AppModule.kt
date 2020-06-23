package com.positivemind.news.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rajeev Tomar on 21,June,2020
 */

@Module
class AppModule(val context:Context) {

    @Provides
    @Singleton
    fun provideContext():Context{
        return context;
    }

}