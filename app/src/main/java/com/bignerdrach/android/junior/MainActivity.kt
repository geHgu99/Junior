package com.bignerdrach.android.junior

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: JokeAdapter? = null
    private val jokeListViewModel: JokeViewModel by lazy {
        ViewModelProviders.of(this).get(JokeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        updateUI()

    }

    private inner class JokeHolder(view: View)
        : RecyclerView.ViewHolder(view) {
            val jokeTextView: TextView = itemView.findViewById(R.id.joke_text)
        }

    private inner class JokeAdapter(var jokes: List<String>)
        : RecyclerView.Adapter<JokeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_joke, parent, false)
            return JokeHolder(view)
        }

        override fun onBindViewHolder(holder: JokeHolder, position: Int) {
            holder.apply {
                jokeTextView.text = jokes[position]
            }
        }

        override fun getItemCount() = jokes.size
    }

    private fun updateUI() {
        val jokes = jokeListViewModel.jokes
        adapter = JokeAdapter(jokes)
        recyclerView.adapter = adapter
    }
}