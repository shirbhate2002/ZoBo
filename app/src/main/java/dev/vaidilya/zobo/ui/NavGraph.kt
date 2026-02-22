package dev.vaidilya.zobo.ui

import android.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.vaidilya.zobo.utils.Destination
import dev.vaidilya.zobo.utils.NavDestination


@Composable
fun NavGraph(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Destination.Home.route){
            HomeScreen(modifier,navController,onItemClick = {
                navController.navigate(Destination.Article.route.replace("{id}",it.toString()))
            })
        }
        composable(
            route=Destination.Bookmark.route,

        ){
            BookMarkScreen()
        }
        composable(
            route=Destination.Article.route,
            arguments = listOf(
                navArgument("id",{
                    defaultValue=1234
                    type= NavType.IntType
                })
            )
        ){
            ArticleScreen(modifier,it.arguments?.getInt("id"))
        }
        composable(
            route=Destination.Profile.route,
        ){
            ProfileScreen(modifier)
        }
    }
}