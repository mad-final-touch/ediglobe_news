package com.example.ediglobe_news.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ediglobe_news.R
import com.example.ediglobe_news.data.ArticleItem
import com.example.ediglobe_news.data.NewsItem


class RecyclerViewAdapter(
  private val articlesList: List<ArticleItem>,
//  private val onClickListener: View.OnClickListener
  private val getArticleOnClicked: (articleItem: ArticleItem) -> Unit,
) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

  class MyViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.itemText)
    val iconView: ImageView = itemView.findViewById(R.id.itemIcon)

  }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.text_row_item, parent, false)
//    view.findViewById<LinearLayout>(R.id.layout_item).orientation = LinearLayout.VERTICAL
    return MyViewHolder(view)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.textView.text = articlesList[position].title
    holder.textView.setOnClickListener {
      getArticleOnClicked(articlesList[position])
    }
    Glide.with(holder.iconView.context)
      .load(articlesList[position].urlToImage)
      .into(holder.iconView)
  }

  override fun getItemCount() = articlesList.size
}
