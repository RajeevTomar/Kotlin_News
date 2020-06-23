package com.positivemind.news.article

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.positivemind.news.R
import com.positivemind.news.base.BaseFragment
import com.positivemind.news.databinding.FragmentArticleBinding
import com.positivemind.news.headline.Article


class ArticleFragment : BaseFragment<FragmentArticleBinding>() {

    lateinit var article: Article
    var interactionListener:ArticleFragmentInteractionListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // parse value from bundle
        receiveBundleData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setArticleDataOnView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is ArticleFragmentInteractionListener)
            interactionListener = context
        else {
            throw RuntimeException(
                context.toString()
                        + " must implement ArticleFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        interactionListener = null
    }

    companion object {

        val ARG_ARTICLE: String = "arg_article"

        /**
         * @return A new instance of fragment ArticleFragment.
         */
        @JvmStatic
//        fun newInstance(article: Article): Fragment {
//            val articleFragment: ArticleFragment = ArticleFragment()
//            val args = Bundle()
//            args.putParcelable(ARG_ARTICLE, article)
//            articleFragment.arguments = args
//            return ArticleFragment()
//        }

        fun newInstance(article: Article) : Fragment{
            return ArticleFragment().apply {
                arguments = Bundle().apply { putParcelable(ARG_ARTICLE, article) }
            }
        }
    }


    //----------------------------------------------------------------------------------------------
    // Base Fragment abstract methods
    //----------------------------------------------------------------------------------------------
    override fun getLayoutId(): Int {
        return R.layout.fragment_article
    }


    //----------------------------------------------------------------------------------------------
    // Private functions
    //----------------------------------------------------------------------------------------------
    private fun receiveBundleData() {
        article = arguments?.getParcelable<Article>(ARG_ARTICLE)!!
    }

    private fun initListener() {
        mViewDataBinding.ivBack.setOnClickListener{
            interactionListener?.onTapBackButton()
        }

    }

    private fun setArticleDataOnView(){
        // image
        // image
        val thumbnailUrl: String? = article.getUrlToImage()
        if (!TextUtils.isEmpty(thumbnailUrl)) {
            Glide.with(mViewDataBinding.ivArticleDetail).load(thumbnailUrl)
                .into(mViewDataBinding.ivArticleDetail)
        }
        // title
        // title
        val title: String? = article.getTitle()
        if (!TextUtils.isEmpty(title)) mViewDataBinding.title.setText(title)
        //source
        //source
        val source: String? = article.getSource()?.getName()
        if (!TextUtils.isEmpty(source)) mViewDataBinding.tvSource.setText(source)
        //publish date
        //publish date
        val date: String? = article.getPublishedDate()
        if (!TextUtils.isEmpty(date)) mViewDataBinding.tvPublishedDate.setText(date)
        val description: String? = article.getDescription()
        if (!TextUtils.isEmpty(description)) mViewDataBinding.description.setText(description)

    }

    interface ArticleFragmentInteractionListener{
        fun onTapBackButton()
    }

}