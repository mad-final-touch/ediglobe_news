package com.example.ediglobe_news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ediglobe_news.R


class RecyclerViewAdapter(private val items: List<String>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.itemText)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.text_row_item, parent, false)
    return MyViewHolder(view)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.textView.text = items[position]
  }

  override fun getItemCount() = items.size
}
