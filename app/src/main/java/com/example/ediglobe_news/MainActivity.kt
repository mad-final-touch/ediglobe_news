package com.example.ediglobe_news

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.ediglobe_news.UI.RecyclerViewAdapter
import com.example.ediglobe_news.adapter.RecyclerViewAdapter
import com.example.ediglobe_news.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ediglobe_news.data.NewsResponse

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    val apiKey = "bd96d9a1fc3d4dd9bc392e4580bff69c" // Replace with your NewsAPI.org key

    RetrofitClient.instance.getTopHeadlines("us", apiKey)
      .enqueue(object : Callback<NewsResponse> {
        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
          if (response.isSuccessful) {
            val news = response.body()?.articles ?: emptyList()
            for (article in news) {
              Log.d("News", "Title: ${article.title}")
            }
          }
        }

        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
          Log.e("News", "Error: ${t.message}")
        }
      })

    val dataset =(1..199).map { it.toString() }
    val customAdapter = RecyclerViewAdapter(dataset)

    val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = customAdapter
  }
}