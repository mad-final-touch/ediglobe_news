package com.example.ediglobe_news.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ediglobe_news.R
import com.example.ediglobe_news.data.ArticleItem

class RecyclerViewAdapter(
  private val articlesList: List<ArticleItem>,
  private val onItemClicked: (articleItem: ArticleItem) -> Unit,
) : RecyclerView.Adapter<RecyclerViewAdapter.ArticleViewHolder>() {

  inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTextView: TextView = itemView.findViewById(R.id.itemText)
    val articleImageView: ImageView = itemView.findViewById(R.id.itemIcon)

    fun bind(articleItem: ArticleItem) {
        titleTextView.text = articleItem.title
        Glide.with(articleImageView.context)
            .load(articleItem.urlToImage)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(articleImageView)

        itemView.setOnClickListener {
            onItemClicked(articleItem)

        }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.text_row_item, parent, false)
    return ArticleViewHolder(view)
  }

  override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
    holder.bind(articlesList[position])
  }

  override fun getItemCount() = articlesList.size

}
