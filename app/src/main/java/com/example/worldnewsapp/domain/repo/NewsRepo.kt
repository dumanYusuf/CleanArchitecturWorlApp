package com.example.worldnewsapp.domain.repo

import com.example.worldnewsapp.data.remote.dto.NewsDto


interface NewsRepo {
    suspend fun getNews():NewsDto
}