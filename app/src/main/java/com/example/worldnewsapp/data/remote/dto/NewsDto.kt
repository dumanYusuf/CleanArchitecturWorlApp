package com.example.worldnewsapp.data.remote.dto

import com.example.worldnewsapp.domain.model.DataModel

data class NewsDto(
    val `data`: List<DataDto>,
    val pagination: PaginationDto
)

fun NewsDto.toDataListModel():List<DataModel>{
    return data.map {news-> DataModel(news.author?:"csa",news.category,news.country,news.description,news.image?:"https://s.france24.com/media/display/e6279b3c-db08-11ee-b7f5-005056bf30b7/w:1024/p:16x9/news_en_1920x1080.jpg",news.language,news.published_at,news.source,news.title) }
}