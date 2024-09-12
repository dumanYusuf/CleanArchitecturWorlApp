package com.example.worldnewsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.worldnewsapp.data.Screen
import com.example.worldnewsapp.domain.model.DataModel
import com.example.worldnewsapp.presentation.news.views.NewsScreen
import com.example.worldnewsapp.presentation.news_detail.views.NewsDetailScreen
import com.example.worldnewsapp.ui.theme.WorldNewsAppTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorldNewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                  //  NewsScreen()
                    ControllerPaggination()
                }
            }
        }
    }
}

@Composable
fun ControllerPaggination() {
    val controller= rememberNavController()
    NavHost(navController = controller, startDestination = Screen.NewsScreen.route) {
        composable(Screen.NewsScreen.route){
            NewsScreen(navController = controller)
        }
        composable(Screen.NewsDetailScreen.route+"/{data}",
            arguments = listOf(
                navArgument("data"){type= NavType.StringType}
            )
        ){
            val jsonNews = it.arguments?.getString("data")
            val decodedJsonNews = URLDecoder.decode(jsonNews, "UTF-8")
            val news = Gson().fromJson(decodedJsonNews, DataModel::class.java)
            NewsDetailScreen(news)
        }
    }
}

