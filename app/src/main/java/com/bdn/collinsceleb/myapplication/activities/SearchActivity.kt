package com.bdn.collinsceleb.myapplication.activities

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bdn.collinsceleb.myapplication.R
import com.bdn.collinsceleb.myapplication.adapters.ArticleListItemRecyclerAdapter
import com.bdn.collinsceleb.myapplication.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val articleDataProvider: ArticleDataProvider = ArticleDataProvider()
    private var articleListItemRecyclerAdapter: ArticleListItemRecyclerAdapter = ArticleListItemRecyclerAdapter()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        search_result_recycler.layoutManager = LinearLayoutManager(this)
        search_result_recycler.adapter = articleListItemRecyclerAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu, menu)

        val  searchItem = menu!!.findItem(R.id.action_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val  searchView = searchItem!!.actionView as androidx.appcompat.widget.SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.requestFocus()
       searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String): Boolean {
               articleDataProvider.getSearch(query, 0, 20) { wikiResult ->
                   articleListItemRecyclerAdapter.currentResults.clear()
                   articleListItemRecyclerAdapter.currentResults.addAll(wikiResult.query!!.pages)
                   runOnUiThread { articleListItemRecyclerAdapter.notifyDataSetChanged() }
               }
               println("updated search")
               return false
           }

           override fun onQueryTextChange(newText: String?): Boolean {
              return false
           }

       })
        return super.onCreateOptionsMenu(menu)
    }
}
