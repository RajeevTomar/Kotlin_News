package com.positivemind.news.data.remote

import com.positivemind.news.headline.Article
import com.positivemind.news.server.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Rajeev Tomar on 22,June,2020
 */
interface HeadlineRemoteSource {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country:String):Single<BaseResponse<List<Article>>>

}