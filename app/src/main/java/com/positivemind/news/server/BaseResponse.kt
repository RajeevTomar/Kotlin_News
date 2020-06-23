package com.positivemind.news.server

/**
 * Created by Rajeev Tomar on 22,June,2020
 */
class BaseResponse<T> {

    private var status: String? = null
    private var articles: T? = null
    private var totalResults = 0


    fun getTotalResults(): Int {
        return totalResults
    }

    fun setTotalResults(totalResults: Int) {
        this.totalResults = totalResults
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getArticles(): T? {
        return articles
    }

    fun setArticles(articles: T) {
        this.articles = articles
    }
}
