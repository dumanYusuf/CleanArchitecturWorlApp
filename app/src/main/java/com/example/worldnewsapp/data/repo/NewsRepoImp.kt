package com.example.worldnewsapp.data.repo

import com.example.worldnewsapp.data.remote.NewsApi
import com.example.worldnewsapp.data.remote.dto.NewsDto
import com.example.worldnewsapp.domain.repo.NewsRepo
import com.example.worldnewsapp.util.Constans
import javax.inject.Inject


class NewsRepoImp @Inject constructor(private val api:NewsApi) :NewsRepo {

    override suspend fun getNews(): NewsDto {
        return api.getNewsApi(Constans.API_KEY)
    }
}