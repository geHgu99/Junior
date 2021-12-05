package com.bignerdrach.android.junior

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdrach.android.junior.api.JokeFetch
import com.bignerdrach.android.junior.list.JokeAdapter

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: JokeAdapter? = null
    private val jokeListViewModel: JokeViewModel by lazy {
        ViewModelProvider(this).get(JokeViewModel::class.java)
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
                addItem(responseString)
            }
        )
    }

    private fun updateUI() {
        val jokes = jokeListViewModel.jokes
        adapter = JokeAdapter(jokes)
        recyclerView.adapter = adapter
    }

    private fun addItem(requestText: String? = null) {
        if (requestText != null) {
            jokeListViewModel.jokes.add(requestText)
            adapter = JokeAdapter(jokeListViewModel.jokes)
            val addPosition = jokeListViewModel.jokes.size
            adapter?.notifyItemInserted(addPosition)
            recyclerView.adapter = adapter
        } else {
            Log.d(TAG, "New item is null")
        }
    }
}