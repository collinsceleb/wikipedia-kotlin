package com.bdn.collinsceleb.myapplication.ui.favourites

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
import com.bdn.collinsceleb.myapplication.adapters.ArticleCardRecyclerAdapter

class FavouritesFragment : Fragment() {

    private lateinit var favouritesViewModel: FavouritesViewModel
    private var favoriteRecycler: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel =
            ViewModelProvider(this).get(FavouritesViewModel::class.java)
        val favoriteView = inflater.inflate(R.layout.fragment_favourites, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        favouritesViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        favoriteRecycler = favoriteView.findViewById(R.id.favorites_article_recycler)
        favoriteRecycler!!.layoutManager = LinearLayoutManager(context)
        favoriteRecycler!!.adapter = ArticleCardRecyclerAdapter()
        return favoriteView
    }
}