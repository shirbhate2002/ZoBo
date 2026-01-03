package dev.vaidilya.zobo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@Composable
fun NavGraph(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.route
    ){
        composable(Destinations.Home.route){
            HomeScreen(modifier,navController)
        }
        composable(
            route=Destinations.Article.route,
            arguments = listOf(
                navArgument("url",{
                    defaultValue="https://google.com/"
                    type= NavType.StringType
                })
            )
        ){
            ArticleScreen(modifier,it.arguments?.getString("url"))
        }
    }
}