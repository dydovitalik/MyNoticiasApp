package com.example.misnoticiasapp

import com.example.misnoticiasapp.api.ApiClient.apiClient

import com.example.misnoticiasapp.models.Article
import android.widget.TextView
import android.os.Bundle
import com.example.misnoticiasapp.models.News
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var articles: MutableList<Article>? = mutableListOf()
    private var adapter: Adapter? = null
    private var topHeadline: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topHeadline = findViewById(R.id.topheadelines)
        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.isNestedScrollingEnabled = false
        LoadJson("")

        val search = findViewById<SearchView>(R.id.search_view)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.length > 2) {
                    LoadJson(query)
                } else {
                    Toast.makeText(this@MainActivity, "Type more than two letters!", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    fun LoadJson(keyword: String) {

        val country = Utils.country
        val language = Utils.language
        val call: Call<News?>? = if (keyword.isNotEmpty()) {
            apiClient.getNewsSearch(keyword, language, "publishedAt", API_KEY)
        } else {
            apiClient.getNews(country, API_KEY)
        }
        call!!.enqueue(object : Callback<News?> {
            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                if (response.isSuccessful && response.body()!!.article != null) {
                    if (articles!!.isNotEmpty()) {
                        articles!!.clear()
                    }
                    articles = response.body()!!.article as MutableList<Article>?
                    adapter = Adapter(articles!!, this@MainActivity)
                    recyclerView!!.adapter = adapter
                    adapter!!.notifyDataSetChanged()
                    topHeadline!!.visibility = View.VISIBLE
                } else {
                    topHeadline!!.visibility = View.INVISIBLE
                    Toast.makeText(this@MainActivity, "No Result", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<News?>, t: Throwable) {
                topHeadline!!.visibility = View.INVISIBLE
            }

        })
    }

    companion object {
        const val API_KEY = "1d15ecea8f4841e0a4ae04a2e92a93c8"
    }
}

