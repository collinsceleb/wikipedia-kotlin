package com.bdn.collinsceleb.myapplication.ui.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bdn.collinsceleb.myapplication.R
import com.bdn.collinsceleb.myapplication.activities.SearchActivity
import com.bdn.collinsceleb.myapplication.adapters.ArticleCardRecyclerAdapter

class ExploreFragment : Fragment() {
    private var searchCardView: CardView? = null
    private var exploreRecycler: RecyclerView? = null
    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProvider(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        val textView: TextView = root.findViewById(R.id.text_explore)
        exploreViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        searchCardView = root.findViewById(R.id.search_card_view)
        exploreRecycler = root.findViewById(R.id.explore_article_recycler)
       searchCardView!!.setOnClickListener {
          val searchWikipediaIntent = Intent(context, SearchActivity::class.java)
           context?.startActivity(searchWikipediaIntent)
       }
       exploreRecycler?.layoutManager = LinearLayoutManager(context)
       exploreRecycler?.adapter = ArticleCardRecyclerAdapter()
        return root
    }
}