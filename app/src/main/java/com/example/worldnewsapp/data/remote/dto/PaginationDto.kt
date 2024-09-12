package com.example.worldnewsapp.data.remote.dto

data class PaginationDto(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val total: Int
)