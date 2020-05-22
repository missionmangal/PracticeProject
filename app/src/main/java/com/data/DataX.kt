package com.data

data class DataX(
    val count_in_response: Int,
    val items: List<Item>,
    val q: String,
    val spelling_alternatives: SpellingAlternatives,
    val start: Int,
    val total_count: Int
)