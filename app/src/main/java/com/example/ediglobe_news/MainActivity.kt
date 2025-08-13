package com.example.ediglobe_news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ediglobe_news.adapter.RecyclerViewAdapter
import com.example.ediglobe_news.data.ArticleItem
import com.example.ediglobe_news.networking.NewsWebAPI

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsFetcher: NewsWebAPI

    private var isGridLayout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        newsFetcher = NewsWebAPI(COUNTRY, API_KEY)

        setupRecyclerView()
        observeNewsUpdates()

        setupToggleLayout()
        newsFetcher.getNews()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.myRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun setupToggleLayout(){
        val toggleButton = findViewById<ImageButton>(R.id.toggleButton)
        toggleButton.setOnClickListener {
            Log.d("MainActivityMyApp", "Toggle button clicked ${isGridLayout}")
            if (recyclerView.layoutManager is GridLayoutManager) {
                toggleButton.setImageResource(R.drawable.ic_grid_view)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                isGridLayout = false

            }else{
                toggleButton.setImageResource(R.drawable.ic_list_view)
                recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                isGridLayout = true
            }
            Log.d("MainActivityMyApp", "${ recyclerView.adapter is RecyclerViewAdapter }")
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun observeNewsUpdates() {
        newsFetcher.articleReady.observe(this) { articleItems ->
            val customAdapter = RecyclerViewAdapter(articleItems, { isGridLayout }) { articleItem ->
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
//            putExtra(NewsDetailActivity.IS_GRID_LAYOUT, isGridLayout)
            // Consider adding author and link if NewsDetailActivity can handle them
            // putExtra(NewsDetailActivity.EXTRA_AUTHOR, articleItem.author)
            // putExtra(NewsDetailActivity.EXTRA_LINK, articleItem.link)
        }
        startActivity(intent)
    }

    companion object {
        private const val API_KEY = "bd96d9a1fc3d4dd9bc392e4580bff69c"
        private const val COUNTRY = "us"
    }
}
