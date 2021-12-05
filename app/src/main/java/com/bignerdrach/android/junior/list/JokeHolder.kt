package com.bignerdrach.android.junior.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdrach.android.junior.R

class JokeHolder(view: View)
    : RecyclerView.ViewHolder(view) {
    val jokeTextView: TextView = itemView.findViewById(R.id.joke_text)
}