package com.positivemind.news.dashboard

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.positivemind.news.R
import com.positivemind.news.article.ArticleFragment
import com.positivemind.news.headline.Article
import com.positivemind.news.headline.HeadlineListFragment

class DashboardActivity : AppCompatActivity(),
    HeadlineListFragment.HeadlineFragmentInteractionListener,
    ArticleFragment.ArticleFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        addHeadLineListFragment()
    }

    override fun onBackPressed() {
        val stackCount = supportFragmentManager.backStackEntryCount
        if (stackCount == 0)
            super.onBackPressed()
        else
            onTapBackButton()
    }

    override fun onTapArticle(article: Article) {
        addArticleFragment(article)
    }

    override fun onTapBackButton() {
        supportFragmentManager.popBackStack()
    }

    //----------------------------------------------------------------------------------------------
    // Private methods
    //----------------------------------------------------------------------------------------------
    private fun addHeadLineListFragment() {
        val headlineListFragment: Fragment = obtainHeadlineListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.dashboard_container, headlineListFragment).commit()
    }

    private fun obtainHeadlineListFragment(): Fragment {
        var headlineListFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.dashboard_container)
        if (headlineListFragment == null)
            headlineListFragment = HeadlineListFragment.newInstance()
        return headlineListFragment;
    }

    private fun addArticleFragment(article: Article) {
        val articleFragment: Fragment = ArticleFragment.newInstance(article)
        supportFragmentManager.beginTransaction().add(
            R.id.dashboard_container,
            articleFragment
        ).addToBackStack(null).commit()
    }


}