package com.example.booksapp.models.apiModels

data class SearchResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)