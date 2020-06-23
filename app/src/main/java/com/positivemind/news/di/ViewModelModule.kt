package com.positivemind.news.di

import com.positivemind.news.data.remote.HeadlineRemoteSource
import com.positivemind.news.headline.HeadlineListViewModel
import com.positivemind.news.utils.rx.AppSchedularProvider
import com.positivemind.news.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rajeev Tomar on 22,June,2020
 */
@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideSchedular(): SchedulerProvider {
        return AppSchedularProvider()
    }

    @Provides
    fun provideHeadlineListViewModel(schedulerProvider: SchedulerProvider,
                                     headlineRemoteSource: HeadlineRemoteSource):HeadlineListViewModel{
        return HeadlineListViewModel(schedulerProvider,headlineRemoteSource)
    }


}