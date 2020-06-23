package com.positivemind.news.di

import com.positivemind.news.data.Constants
import com.positivemind.news.data.remote.HeadlineRemoteSource
import com.positivemind.news.server.Url
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Rajeev Tomar on 22,June,2020
 */
@Module
class RetrofitModule {

    @Provides
    fun provideHeadlineRemoteSource(retrofit: Retrofit):HeadlineRemoteSource{
        return retrofit.create(HeadlineRemoteSource::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Url.NEWS_API_UR)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOKHttpClient(headerInterceptor: Interceptor):OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor(headerInterceptor).build()
    }

    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", Constants.API_KEY)
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
    }
}