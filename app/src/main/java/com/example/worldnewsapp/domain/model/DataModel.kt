package com.example.worldnewsapp.domain.model

data class DataModel(
    val author: String?,
    val category: String,
    val country: String,
    val description: String,
    val image: String? = "https://s.france24.com/media/display/e6279b3c-db08-11ee-b7f5-005056bf30b7/w:1024/p:16x9/news_en_1920x1080.jpg",
    val language: String,
    val published_at: String,
    val source: String,
    val title: String,
)
