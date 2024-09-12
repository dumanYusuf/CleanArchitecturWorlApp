package com.example.worldnewsapp.data.di

import com.example.worldnewsapp.data.remote.NewsApi
import com.example.worldnewsapp.data.repo.NewsRepoImp
import com.example.worldnewsapp.domain.repo.NewsRepo
import com.example.worldnewsapp.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
     fun newsProvides(): NewsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASE_URL)
            .build().create(NewsApi::class.java)
    }

    @Singleton
    @Provides

    fun providesNewsRepo(api: NewsApi):NewsRepo{
        return NewsRepoImp(api)
    }


}