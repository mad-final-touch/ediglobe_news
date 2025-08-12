package com.example.ediglobe_news.data

data class NewsItem(val title: String, val imageUrl: String?)
data class ArticleItem(
//  val source: Source,
//  val author: String?,
  val title: String?,
  val description: String?,
//  val url: String?,
  val urlToImage: String?,
//  val publishedAt: String?,
  val content: String?
)