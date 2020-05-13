package com.bdn.collinsceleb.myapplication.viewholders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bdn.collinsceleb.myapplication.R
import com.bdn.collinsceleb.myapplication.activities.ArticleDetailsActivity
import com.bdn.collinsceleb.myapplication.models.WikiPage
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val articleImageView: ImageView = itemView.findViewById(R.id.article_image)
    private val articleTitleTextView: TextView = itemView.findViewById(R.id.article_title)

   private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener { view: View? ->
            var detailPageIntent = Intent(itemView.context, ArticleDetailsActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updatePage(page: WikiPage) {
        currentPage = page

       articleTitleTextView.text = page.title

        // Load image with Picasso
       if (page.thumbnail != null) {
           Picasso.get().load(page.thumbnail!!.source).into(articleImageView)
       }
    }
}