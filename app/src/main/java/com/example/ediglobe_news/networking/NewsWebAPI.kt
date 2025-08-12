package com.example.ediglobe_news.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ediglobe_news.R
import com.example.ediglobe_news.adapter.RecyclerViewAdapter
import com.example.ediglobe_news.data.ArticleItem
import com.example.ediglobe_news.data.NewsResponse
import com.example.ediglobe_news.data.NewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsWebAPI(country:String,apikey:String){
  val apiKey =apikey
  val country = country
  var articleReady = MutableLiveData<List<ArticleItem>>()
  fun getNews(){
    RetrofitClient.instance.getTopHeadlines(country, apiKey)
      .enqueue(object : Callback<NewsResponse> {
        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
          if (response.isSuccessful) {
            val articles = response.body()?.articles ?: emptyList()
//            Log.d("News", "Response: ${articles.get(0).content}")
            articleReady.postValue(articles.map { ArticleItem(it.title,it.description,it.urlToImage,it.content)})
          }
        }

        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
          articleReady.postValue(emptyList())
          Log.e("News", "Error: ${t.message}")
        }
      })
  }
}