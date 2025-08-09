package com.example.ediglobe_news

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.ediglobe_news.UI.RecyclerViewAdapter
import com.example.ediglobe_news.adapter.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)

    val dataset =(1..199).map { it.toString() }
    val customAdapter = RecyclerViewAdapter(dataset)

    val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = customAdapter
  }
}