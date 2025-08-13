package com.example.ediglobe_news.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ediglobe_news.data.ArticleItem
import com.example.ediglobe_news.data.NewsResponse
// Removed NewsItem as it's implicitly used via NewsResponse and mapped to ArticleItem
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
            val articlesFromResponse = response.body()?.articles ?: emptyList()
            articleReady.postValue(articlesFromResponse.map {
                ArticleItem(it.title,it.description,it.urlToImage,it.content)
            })
          } else {
            Log.e("News", "API Error Response Code: ${response.code()}")
            articleReady.postValue(emptyList()) // Or a specific error state
          }
        }

        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
          articleReady.postValue(emptyList()) // Or a specific error state
          Log.e("News", "Network Failure: ${t.message}", t)
        }
      })
  }
}
