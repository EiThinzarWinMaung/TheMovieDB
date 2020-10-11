package com.example.themoviedb.model.similar

data class Similar(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)