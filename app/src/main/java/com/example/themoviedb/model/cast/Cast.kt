package com.example.themoviedb.model.cast

data class Cast(
    val cast: List<CastX>,
    val crew: List<Crew>,
    val id: Int
)