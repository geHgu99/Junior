package com.bignerdrach.android.junior

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdrach.android.junior.api.GeekJokesApi
import com.bignerdrach.android.junior.api.JokeFetch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "MainActivity"

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

        updateUI()
    }

    override fun onStart() {
        super.onStart()

        val jokeLiveData: LiveData<String> = JokeFetch().fetchContents()
        jokeLiveData.observe(
            this,
            Observer { responseString ->
                updateUI(responseString)
            }
        )
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

    private fun updateUI(requestText: String? = null) {
        val jokes = jokeListViewModel.jokes
        if (requestText != null) {
            jokes.add(requestText)
            Log.d(TAG, jokes.size.toString())
        }
        adapter = JokeAdapter(jokes)
        recyclerView.adapter = adapter
    }
}