package com.example.ediglobe_news

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
//import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    val imageUrl = intent.getStringExtra("imageUrl")
    val title = intent.getStringExtra("title")
//    val author = intent.getStringExtra("author")
    val description = intent.getStringExtra("description")
    val content = intent.getStringExtra("content")
//    val link = intent.getStringExtra("link")

    val newsTitle = findViewById<TextView>(R.id.newsTitle)
//    val newsAuthor = findViewById<TextView>(R.id.newsAuthor)
    val newsDescription = findViewById<TextView>(R.id.newsDescription)
    val newsContent = findViewById<TextView>(R.id.newsContent)
    val newsImage = findViewById<ImageView>(R.id.newsImage)

    Glide.with(this)
      .load(imageUrl)
      .into(newsImage)


    Log.d("imageUrl", imageUrl.toString())
    Log.d("title", title.toString())
//    Log.d("author", author.toString())
    Log.d("description", description.toString())
    Log.d("content", content.toString())
//    Log.d("link", link.toString())

    newsTitle.text = title
//    newsAuthor.text = author
    newsDescription.text = description
    newsContent.text = content
//    newsLink.text = link

  }
}