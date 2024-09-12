package com.example.worldnewsapp.data

sealed class Screen(val route:String) {
   object NewsScreen:Screen("news_screen")
    object NewsDetailScreen:Screen("news_detail_screen")

}