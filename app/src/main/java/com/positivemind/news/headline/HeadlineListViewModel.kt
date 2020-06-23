package com.positivemind.news.headline

import androidx.lifecycle.MutableLiveData
import com.positivemind.news.base.BaseViewModel
import com.positivemind.news.data.Constants
import com.positivemind.news.data.remote.HeadlineRemoteSource
import com.positivemind.news.utils.rx.SchedulerProvider

/**
 * Created by Rajeev Tomar on 22,June,2020
 */
class HeadlineListViewModel : BaseViewModel {

    //----------------------------------------------------------------------------------------------
    // Class Variables
    //----------------------------------------------------------------------------------------------
    private val headlineListMutableLiveData =
        MutableLiveData<List<Article>>()
    private val throwableMutableLiveData =
        MutableLiveData<Throwable>()
    private var headlineRemoteSource:HeadlineRemoteSource;


    constructor(schedulerProvider: SchedulerProvider,
                headlineRemoteSource: HeadlineRemoteSource) : super(schedulerProvider)
    {
        this.headlineRemoteSource = headlineRemoteSource
    }


    //----------------------------------------------------------------------------------------------
    // Public methods
    //----------------------------------------------------------------------------------------------
    fun getHeadlineListMutableLiveData(): MutableLiveData<List<Article>> {
        return headlineListMutableLiveData
    }

    fun getThrowableMutableLiveData(): MutableLiveData<Throwable> {
        return throwableMutableLiveData
    }


    fun fetchTopHeadlinesFromRemote() {
        compositeDisposable.add(headlineRemoteSource.getTopHeadlines(Constants.INDIA_COUNTRY_CODE)
            .subscribeOn(schedularProvider.io()).observeOn(schedularProvider.ui())
            .subscribe(
                { headlineDataResponse ->
                    if (headlineDataResponse != null) {
                        val articles: List<Article>? =
                            headlineDataResponse.getArticles()
                        headlineListMutableLiveData.setValue(articles)
                    }
                }, { throwable -> throwableMutableLiveData.setValue(throwable) }
            ))
    }

}