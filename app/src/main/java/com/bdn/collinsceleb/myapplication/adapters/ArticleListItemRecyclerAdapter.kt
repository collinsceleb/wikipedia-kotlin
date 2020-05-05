package com.bdn.collinsceleb.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bdn.collinsceleb.myapplication.R
import com.bdn.collinsceleb.myapplication.viewholders.ListItemHolder

class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var listItemHolder = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItemHolder)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}