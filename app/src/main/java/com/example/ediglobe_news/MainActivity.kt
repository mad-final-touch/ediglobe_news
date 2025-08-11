package com.example.ediglobe_news

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
    recyclerView.layoutManager = LinearLayoutManager(this)
    newsFetcher.newsReady.observe(this) { newsItems ->
      var customAdapter = RecyclerViewAdapter(newsItems)
      recyclerView.adapter = customAdapter
    }
    newsFetcher.getNews()
  }
}