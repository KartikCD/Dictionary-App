package com.kartikcd.wordsearch.data.model

data class DictionaryItem(
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>,
    val word: String
)