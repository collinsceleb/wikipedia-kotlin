package com.bdn.collinsceleb.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bdn.collinsceleb.myapplication.R
import com.bdn.collinsceleb.myapplication.models.WikiPage
import com.bdn.collinsceleb.myapplication.viewholders.CardViewHolder



class ArticleCardRecyclerAdapter :RecyclerView.Adapter<CardViewHolder>() {

    val currentResults: ArrayList<WikiPage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        var cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item, parent, false)
        return CardViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        var page = currentResults[position]

        holder.updatePage(page)
    }

    override fun getItemCount(): Int {
        return currentResults.size
    }
}