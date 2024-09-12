package com.example.worldnewsapp.data.remote.dto

import com.example.worldnewsapp.domain.model.DataModel

data class NewsDto(
    val `data`: List<DataDto>,
    val pagination: PaginationDto
)

fun NewsDto.toDataListModel():List<DataModel>{
    return data.map {news-> DataModel(news.author?:"csa",news.category,news.country,news.description,news.image?:"sa",news.language,news.published_at,news.source,news.title) }
}