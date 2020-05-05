package com.bdn.collinsceleb.myapplication.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bdn.collinsceleb.myapplication.R


class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleListImageView: ImageView = itemView.findViewById(R.id.result_icon)
    private val articleListTextView: TextView = itemView.findViewById(R.id.result_title)
}