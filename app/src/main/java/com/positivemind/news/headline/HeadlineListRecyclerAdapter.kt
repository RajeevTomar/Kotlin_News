package com.positivemind.news.headline

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.positivemind.news.R
import com.positivemind.news.databinding.ViewHeadlineListBinding

/**
 * Created by Rajeev Tomar on 23,June,2020
 */
class HeadlineListRecyclerAdapter :
    RecyclerView.Adapter<HeadlineListRecyclerAdapter.DataViewHolder> {

    private var headlineListItemListener: HeadlineListItemListener
    private var articles: List<Article> = ArrayList();

    constructor(headlineListItemListener: HeadlineListItemListener) {
        this.headlineListItemListener = headlineListItemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.view_headline_list,
            FrameLayout(parent.context), false
        )
        return DataViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.articles.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val article:Article = articles.get(position)
        holder.setViewModel(article)
        holder.itemViewBinding.root.setOnClickListener {
            headlineListItemListener.onClickArticle(article)
        }
    }

    fun updateHeadlines(articles: List<Article>?) {
        if (articles == null || articles.size == 0)
            return
        this.articles = articles
        notifyDataSetChanged()

    }

    class DataViewHolder : RecyclerView.ViewHolder {
        lateinit var itemViewBinding: ViewHeadlineListBinding


        constructor(itemView: View) : super(itemView) {
            bind()
        }

        fun bind() {
            itemViewBinding = DataBindingUtil.bind(itemView)!!
        }

        fun unbind() {
            if (itemViewBinding != null)
                itemViewBinding.unbind()
        }

        fun setViewModel(article: Article) {
            if (itemViewBinding != null) {
                itemViewBinding.itemData = article
                //load image
                loadImage(itemViewBinding, article)
            }
        }

        fun loadImage(itemViewBinding: ViewHeadlineListBinding, article: Article) {
            if (article != null) {
                val thumbnailUrl: String? = article.getUrlToImage()
                if (!TextUtils.isEmpty(thumbnailUrl)) {
                    itemViewBinding.ivArticle.setVisibility(View.VISIBLE)
                    Glide.with(itemViewBinding.ivArticle).load(thumbnailUrl)
                        .transform(CenterCrop(), RoundedCorners(16)).into(
                            itemViewBinding.ivArticle
                        )
                } else {
                    itemViewBinding.ivArticle.setVisibility(View.GONE)
                }
            }
        }

    }
}