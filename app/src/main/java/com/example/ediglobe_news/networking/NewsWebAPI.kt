package com.example.ediglobe_news.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ediglobe_news.R
import com.example.ediglobe_news.adapter.RecyclerViewAdapter
import com.example.ediglobe_news.data.NewsResponse
import com.example.ediglobe_news.view_model.NewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsWebAPI(country:String,apikey:String){
  val apiKey =apikey
  val country = country
  var newsReady = MutableLiveData<List<NewsItem>>()
  fun getNews(){
    RetrofitClient.instance.getTopHeadlines(country, apiKey)
      .enqueue(object : Callback<NewsResponse> {
        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
          if (response.isSuccessful) {
            val articles = response.body()?.articles ?: emptyList()
            newsReady.postValue(articles.map { NewsItem(it.title ?: "" ,it.urlToImage)})
          }
        }

        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
          newsReady.postValue(emptyList())
          Log.e("News", "Error: ${t.message}")
        }
      })
  }
}