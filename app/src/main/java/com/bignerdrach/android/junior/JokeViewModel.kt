package com.bignerdrach.android.junior

import androidx.lifecycle.ViewModel

class JokeViewModel : ViewModel(){

    val jokes = mutableListOf<String>()

    init {
        for (i in 0 until 2) {
            jokes.add("Joke number $i")
        }
    }
}