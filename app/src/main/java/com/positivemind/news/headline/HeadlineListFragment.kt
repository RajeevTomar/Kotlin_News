package com.positivemind.news.headline

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.positivemind.news.NewsApplication
import com.positivemind.news.R
import com.positivemind.news.base.BaseFragment
import com.positivemind.news.databinding.FragmentHeadlineListBinding
import javax.inject.Inject


class HeadlineListFragment : BaseFragment<FragmentHeadlineListBinding>(),HeadlineListItemListener{

    @Inject
    lateinit var listViewModel: HeadlineListViewModel
    lateinit var headlineListAdapter: HeadlineListRecyclerAdapter
    var headlineFragmentInteractionListener: HeadlineFragmentInteractionListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make Dagger instantiate @Inject fields
        (context?.applicationContext as NewsApplication).appComponent.inject(this)
        // Now viewModel is available

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.toolbarHeadline.title= "Headlines"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLiveData()
        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        listViewModel.fetchTopHeadlinesFromRemote()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HeadlineFragmentInteractionListener) {
            headlineFragmentInteractionListener =
                context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement HeadlineFragmentInteractionListener"
            )
        }


    }

    override fun onDetach() {
        super.onDetach()
        headlineFragmentInteractionListener=null
    }

    companion object {
        /**
         * @return A new instance of fragment HeadlineListFragment.
         */
        @JvmStatic
        fun newInstance() =
            HeadlineListFragment()
    }


    //----------------------------------------------------------------------------------------------
    // Base Fragment abstract methods
    //----------------------------------------------------------------------------------------------
    override fun getLayoutId(): Int {
       return R.layout.fragment_headline_list
    }


    //----------------------------------------------------------------------------------------------
    // Private functions
    //----------------------------------------------------------------------------------------------
    private fun initLiveData(){
        // remote datat source
        listViewModel.getHeadlineListMutableLiveData().observe(this,
            Observer { articles->displayHeadlines(articles) })

        // catch errors
        listViewModel.getThrowableMutableLiveData().observe(this, Observer { error->
            println(error.message)
        })
    }

    private fun initAdapter(){
        val recyclerView:RecyclerView = mViewDataBinding.rvHeadline
        recyclerView.layoutManager=LinearLayoutManager(context);
        headlineListAdapter = HeadlineListRecyclerAdapter(this)
        recyclerView.adapter=headlineListAdapter
    }

    private fun displayHeadlines(articles:List<Article>){
        headlineListAdapter.updateHeadlines(articles)
    }

    //----------------------------------------------------------------------------------------------
    // HeadlineListItemListener implemented methods
    //----------------------------------------------------------------------------------------------
    override fun onClickArticle(article: Article) {
        headlineFragmentInteractionListener?.onTapArticle(article)
    }

    interface HeadlineFragmentInteractionListener{
        fun onTapArticle(article: Article)
    }
}