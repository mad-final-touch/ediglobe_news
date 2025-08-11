package com.example.ediglobe_news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ediglobe_news.R
import com.example.ediglobe_news.view_model.NewsItem


class RecyclerViewAdapter(private val newsList: List<NewsItem>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.itemText)
    val iconView: ImageView = itemView.findViewById(R.id.itemIcon)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.text_row_item, parent, false)
    return MyViewHolder(view)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.textView.text = newsList[position].title
    Glide.with(holder.itemView.context)
      .load(newsList[position].imageUrl)
      .into(holder.iconView)
  }

  override fun getItemCount() = newsList.size
}
