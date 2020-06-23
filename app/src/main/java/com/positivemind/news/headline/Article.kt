package com.positivemind.news.headline

import android.os.Parcel
import android.os.Parcelable
import com.positivemind.news.utils.rx.Utils

/**
 * Created by Rajeev Tomar on 22,June,2020
 */
class Article() :Parcelable {

    private var source: Source? = null
    private var author: String? = null
    private var title: String? = null
    private var description: String? = null
    private var url: String? = null
    private var urlToImage: String? = null
    private var publishedAt: String? = null
    private var content: String? = null

    constructor(parcel: Parcel) : this() {
        author = parcel.readString()
        title = parcel.readString()
        description = parcel.readString()
        url = parcel.readString()
        urlToImage = parcel.readString()
        publishedAt = parcel.readString()
        content = parcel.readString()
        source = parcel.readParcelable(ClassLoader.getSystemClassLoader())
    }

    fun getSource(): Source? {
        return source
    }

    fun setSource(source: Source?) {
        this.source = source
    }

    fun getAuthor(): String? {
        return author
    }

    fun setAuthor(author: String?) {
        this.author = author
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getUrlToImage(): String? {
        return urlToImage
    }

    fun setUrlToImage(urlToImage: String?) {
        this.urlToImage = urlToImage
    }

    fun getPublishedDate(): String? {
        return Utils.getFormattedDate(
            Utils.YMDMT_FORMAT,
            Utils.YMD_FORMAT, publishedAt
        )
    }

    fun getPublishedAt(): String? {
        return publishedAt
    }


    fun setPublishedAt(publishedAt: String?) {
        this.publishedAt = publishedAt
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }


    class Source() :Parcelable {

        private var id: String? = null
        private var name: String? = null

        constructor(parcel: Parcel) : this() {
            id = parcel.readString()
            name = parcel.readString()
        }


        fun getId(): String? {
            return id
        }

        fun setId(id: String?) {
            this.id = id
        }

        fun getName(): String? {
            return name
        }

        fun setName(name: String?) {
            this.name = name
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Source> {
            override fun createFromParcel(parcel: Parcel): Source {
                return Source(parcel)
            }

            override fun newArray(size: Int): Array<Source?> {
                return arrayOfNulls(size)
            }
        }


    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
        parcel.writeParcelable(source,flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}