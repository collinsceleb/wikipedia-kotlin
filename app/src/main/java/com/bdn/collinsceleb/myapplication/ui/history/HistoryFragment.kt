package com.bdn.collinsceleb.myapplication.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bdn.collinsceleb.myapplication.R
import com.bdn.collinsceleb.myapplication.adapters.ArticleListItemRecyclerAdapter

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private var historyRecyler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)
        val historyView = inflater.inflate(R.layout.fragment_history, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        historyViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        historyRecyler = historyView.findViewById(R.id.history_article_recycler)
        historyRecyler!!.layoutManager = LinearLayoutManager(context)
        historyRecyler!!.adapter = ArticleListItemRecyclerAdapter()
        return historyView
    }
}