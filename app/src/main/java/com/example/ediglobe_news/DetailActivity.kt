package com.example.ediglobe_news

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class NewsDetailActivity : AppCompatActivity() { // Class name is NewsDetailActivity

  companion object {
    const val EXTRA_IMAGE_URL = "com.example.ediglobe_news.EXTRA_IMAGE_URL"
    const val EXTRA_TITLE = "com.example.ediglobe_news.EXTRA_TITLE"
    const val EXTRA_DESCRIPTION = "com.example.ediglobe_news.EXTRA_DESCRIPTION"
    const val EXTRA_CONTENT = "com.example.ediglobe_news.EXTRA_CONTENT"
    // Add for author and link if you plan to use them
    // const val EXTRA_AUTHOR = "com.example.ediglobe_news.EXTRA_AUTHOR"
    // const val EXTRA_LINK = "com.example.ediglobe_news.EXTRA_LINK"
  }

  private lateinit var newsTitleTextView: TextView
  private lateinit var newsDescriptionTextView: TextView
  private lateinit var newsContentTextView: TextView
  private lateinit var newsImageView: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    setupUI()

    val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
    val title = intent.getStringExtra(EXTRA_TITLE)
    val description = intent.getStringExtra(EXTRA_DESCRIPTION)
    val content = intent.getStringExtra(EXTRA_CONTENT)

    populateUI(imageUrl, title, description, content)
  }

  private fun setupUI() {
    newsTitleTextView = findViewById(R.id.newsTitle)
    newsDescriptionTextView = findViewById(R.id.newsDescription)
    newsContentTextView = findViewById(R.id.newsContent)
    newsImageView = findViewById(R.id.newsImage)
    // newsAuthorTextView = findViewById(R.id.newsAuthor) // If you add author
  }

  private fun populateUI(imageUrl: String?, title: String?, description: String?, content: String?) {
    newsTitleTextView.text = title ?: "No Title"
    newsDescriptionTextView.text = description ?: "No Description"
    newsContentTextView.text = content ?: "No Content"
    // newsAuthorTextView.text = author ?: "Unknown Author" // If you add author

    if (imageUrl != null) {
      Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.ic_launcher_background) // Replace with your placeholder
        .error(R.drawable.ic_launcher_foreground)       // Replace with your error drawable
        .into(newsImageView)
    } else {
      // Optionally, load a default image or hide the ImageView
      newsImageView.setImageResource(R.drawable.ic_launcher_foreground) // Example
    }

    Log.d("NewsDetailActivity", "Image URL: $imageUrl")
    Log.d("NewsDetailActivity", "Title: $title")
    Log.d("NewsDetailActivity", "Description: $description")
    Log.d("NewsDetailActivity", "Content: $content")
  }
}
