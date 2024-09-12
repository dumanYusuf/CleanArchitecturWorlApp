package com.example.worldnewsapp.presentation.news

import com.example.worldnewsapp.domain.model.DataModel

data class NewsState(
    val newsList:List<DataModel> = emptyList(),
    val isLoading:Boolean =false,
    val errorMessage:String =""
)