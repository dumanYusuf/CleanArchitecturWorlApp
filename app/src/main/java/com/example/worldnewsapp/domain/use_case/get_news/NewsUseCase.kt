package com.example.worldnewsapp.domain.use_case.get_news

import android.util.Log
import com.example.worldnewsapp.data.remote.dto.toDataListModel
import com.example.worldnewsapp.domain.model.DataModel
import com.example.worldnewsapp.domain.repo.NewsRepo
import com.example.worldnewsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NewsUseCase @Inject constructor(private val repo:NewsRepo) {


     fun newsApp():Flow<Resource<List<DataModel>>> = flow {
         Log.e("usecase","usecase start")
         try {
             emit(Resource.Loading())
             val newsList=repo.getNews()
             if (newsList.data.isNotEmpty()){
                 emit(Resource.Success(newsList.toDataListModel()))
                 Log.e("succsess","usecase Succseecc")
             }
         }
         catch (e:Exception){
             emit(Resource.Error("Error"))
             Log.e("succsess","usecase Succseecc:${e.localizedMessage}")
         }

    }
}