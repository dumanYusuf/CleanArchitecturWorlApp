package com.example.worldnewsapp.presentation.news.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.worldnewsapp.data.Screen
import com.example.worldnewsapp.presentation.news.NewsViewModel
import com.google.gson.Gson
import java.net.URLEncoder


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsViewModel= hiltViewModel()
) {

    val state=viewModel.state.value
    val context= LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "News App")
            })
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(viewModel.state.value.newsList) { news ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.fillMaxWidth().padding(10.dp).clickable {
                            val newsObject = Gson().toJson(news)
                            val encodedNewsObject = URLEncoder.encode(newsObject, "UTF-8")
                            navController.navigate(Screen.NewsDetailScreen.route+"/$encodedNewsObject")
                        }) {

                            Text(
                                modifier = Modifier.padding(horizontal = 5.dp),
                                text = news.title, fontSize = 20.sp
                            )
                            Image(
                                modifier = Modifier.fillMaxWidth().size(250.dp),
                                painter = rememberAsyncImagePainter(
                                    model = news.image,
                                    imageLoader = ImageLoader(context)
                                ),
                                contentDescription = "", contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            if (state.errorMessage.isNotBlank()) {
                Text(
                    text = state.errorMessage,
                    color = Color.Red,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(modifier = Modifier.fillMaxSize()) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = Color.Red,
                        modifier = Modifier.align(alignment = Alignment.Center).size(50.dp)
                    )
                }
            }
        })
}