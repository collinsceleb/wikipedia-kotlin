package com.bdn.collinsceleb.myapplication.ui.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bdn.collinsceleb.myapplication.R
import com.bdn.collinsceleb.myapplication.activities.SearchActivity
import com.bdn.collinsceleb.myapplication.adapters.ArticleCardRecyclerAdapter
import com.bdn.collinsceleb.myapplication.providers.ArticleDataProvider

class ExploreFragment : Fragment() {
    private val articleDataProvider: ArticleDataProvider = ArticleDataProvider()
    private var searchCardView: CardView? = null
    private var exploreRecycler: RecyclerView? = null
    private lateinit var exploreViewModel: ExploreViewModel
    private var articleCardRecyclerAdapter: ArticleCardRecyclerAdapter =
        ArticleCardRecyclerAdapter()
    var refresher: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProvider(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        val textView: TextView = root.findViewById(R.id.article_search)
        exploreViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        refresher = root.findViewById(R.id.refresher)
        refresher?.setOnRefreshListener {
            getRandomArticles()
        }
        getRandomArticles()
        searchCardView = root.findViewById(R.id.search_card_view)
        exploreRecycler = root.findViewById(R.id.explore_article_recycler)
        searchCardView!!.setOnClickListener {
            val searchWikipediaIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(searchWikipediaIntent)
        }
        exploreRecycler?.layoutManager = LinearLayoutManager(context)
        exploreRecycler?.adapter = articleCardRecyclerAdapter
        return root
    }

    private fun getRandomArticles() {
        refresher?.isRefreshing = true

        try {
            articleDataProvider.getRandom(20) { wikiResult ->
                articleCardRecyclerAdapter.currentResults.clear()
                articleCardRecyclerAdapter.currentResults.addAll(wikiResult.query!!.pages)
                activity?.runOnUiThread {
                    articleCardRecyclerAdapter.notifyDataSetChanged()
                    refresher?.isRefreshing = false
                }
            }
        }
        catch (exception : Exception) {
            val alertBuilder = activity?.let { AlertDialog.Builder(it) }
            alertBuilder?.setMessage(exception.message)?.setTitle("oops")
            val  dialog = alertBuilder?.create()
            dialog?.show()

        }

    }
}