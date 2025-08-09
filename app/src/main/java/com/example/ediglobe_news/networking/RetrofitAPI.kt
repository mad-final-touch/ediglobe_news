package com.example.ediglobe_news.networking

import com.example.ediglobe_news.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
  @GET("top-headlines")
  fun getTopHeadlines(
    @Query("country") country: String,
    @Query("apiKey") apiKey: String
  ): Call<NewsResponse>
}
