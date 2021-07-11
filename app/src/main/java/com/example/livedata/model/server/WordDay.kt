package com.example.livedata.model.server

data class WordDay(
    val anotonyms: List<String>,
    val generic: String,
    val header: String,
    val synonym: List<String>,
    val word: String
)