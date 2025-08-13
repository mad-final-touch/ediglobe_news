package com.example.ediglobe_news

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ediglobe_news.adapter.RecyclerViewAdapter
import com.example.ediglobe_news.data.ArticleItem
import com.example.ediglobe_news.networking.NewsWebAPI

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsFetcher: NewsWebAPI // Declare at class level if used in multiple methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        newsFetcher = NewsWebAPI(COUNTRY, API_KEY)

        setupRecyclerView()
        observeNewsUpdates()

        newsFetcher.getNews()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.myRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // If you plan to add toggle logic for GridLayoutManager,
        // it would be initialized or updated here.
    }

    private fun observeNewsUpdates() {
        newsFetcher.articleReady.observe(this) { articleItems ->
            val customAdapter = RecyclerViewAdapter(articleItems) { articleItem ->
                navigateToDetail(articleItem)
            }
            recyclerView.adapter = customAdapter
        }
    }

    private fun navigateToDetail(articleItem: ArticleItem) {
        val intent = Intent(this@MainActivity, NewsDetailActivity::class.java).apply {
            putExtra(NewsDetailActivity.EXTRA_IMAGE_URL, articleItem.urlToImage)
            putExtra(NewsDetailActivity.EXTRA_TITLE, articleItem.title)
            putExtra(NewsDetailActivity.EXTRA_DESCRIPTION, articleItem.description)
            putExtra(NewsDetailActivity.EXTRA_CONTENT, articleItem.content)
            // Consider adding author and link if NewsDetailActivity can handle them
            // putExtra(NewsDetailActivity.EXTRA_AUTHOR, articleItem.author)
            // putExtra(NewsDetailActivity.EXTRA_LINK, articleItem.link)
        }
        startActivity(intent)
    }

    companion object {
        private const val API_KEY = "bd96d9a1fc3d4dd9bc392e4580bff69c" // For real apps, use local.properties
        private const val COUNTRY = "us"
    }
}
