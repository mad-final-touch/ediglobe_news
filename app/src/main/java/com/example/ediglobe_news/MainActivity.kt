package com.example.ediglobe_news

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ediglobe_news.adapter.RecyclerViewAdapter
import com.example.ediglobe_news.networking.NewsWebAPI


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    val apiKey = "bd96d9a1fc3d4dd9bc392e4580bff69c"
    val newsFetcher = NewsWebAPI("us", apiKey)
    val recyclerView: RecyclerView = findViewById(R.id.myRecyclerView)
//    recyclerView.layoutManager = LinearLayoutManager(this)
//    recyclerView.layoutManager = GridLayoutManager(this,2)
    // Start with a Linear layout (row view)
//    var isGrid = false
    recyclerView.layoutManager = LinearLayoutManager(this)

// Toggle between row and grid
//    toggleButton.setOnClickListener {
//      isGrid = !isGrid
//      recyclerView.layoutManager = if (isGrid) {
//        GridLayoutManager(this, 2) // 2 columns
//      } else {
//        LinearLayoutManager(this)
//      }
//    }
    newsFetcher.articleReady.observe(this) { articleItems ->
      var customAdapter = RecyclerViewAdapter(
        articleItems
        ){ articleItem ->
        val intent = Intent(this@MainActivity, NewsDetailActivity::class.java).apply {
        putExtra("imageUrl", articleItem.urlToImage)
        putExtra("title", articleItem.title)
//        putExtra("author", articleItem.author)
        putExtra("description", articleItem.description)
        putExtra("content", articleItem.content)
//        putExtra("link", articleItem.link)
      }
        startActivity(intent)

      }
      recyclerView.adapter = customAdapter
    }
    newsFetcher.getNews()
  }
}