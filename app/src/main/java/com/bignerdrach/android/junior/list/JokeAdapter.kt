package com.bignerdrach.android.junior.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdrach.android.junior.R


class JokeAdapter(var jokes: List<String>)
    : RecyclerView.Adapter<JokeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_joke, parent, false)
        return JokeHolder(view)
    }

    override fun onBindViewHolder(holder: JokeHolder, position: Int) {
        holder.apply {
            jokeTextView.text = jokes[position]
        }
    }

    override fun getItemCount() = jokes.size


}