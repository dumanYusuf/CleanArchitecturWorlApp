package com.example.worldnewsapp.presentation.news

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.worldnewsapp.domain.use_case.get_news.NewsUseCase
import com.example.worldnewsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: NewsUseCase):ViewModel() {


    private val _state= mutableStateOf(NewsState())
    val state:State<NewsState> = _state

    private var job:Job?=null

    init {
        getNews()
    }

    private fun getNews(){
        Log.e("start","viewModel Start")
        job?.cancel()

        job=useCase.newsApp().onEach {
            when(it){
                is Resource.Success->{
                    _state.value= NewsState(newsList = it.data?: emptyList())
                    Log.e("succsess","viewModel Succseecc")
                }
                is Resource.Error->{
                    _state.value=NewsState(errorMessage = it.message?:"Error viewModel")
                    Log.e("Error","viewModel Error:${it.message}")
                }
                is Resource.Loading->{
                    _state.value=NewsState(isLoading = true)
                    Log.e("loading","viewModel loading")
                }
            }
        }.launchIn(viewModelScope)
    }
}