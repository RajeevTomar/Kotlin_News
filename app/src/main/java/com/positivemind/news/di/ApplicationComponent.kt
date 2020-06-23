package com.positivemind.news.di

import androidx.fragment.app.Fragment
import com.positivemind.news.article.ArticleFragment
import com.positivemind.news.headline.HeadlineListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Rajeev Tomar on 21,June,2020
 */
@Singleton
@Component(modules = [AppModule::class,RetrofitModule::class,ViewModelModule::class])
interface ApplicationComponent {

    fun inject(headlineListFragment: HeadlineListFragment);
    fun inject(articleFragment: ArticleFragment)
}