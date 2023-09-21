package com.example.credcomposeassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.credcomposeassignment.feature.category.CategoryPageUI
import com.example.credcomposeassignment.feature.category.CategoryViewModel
import com.example.credcomposeassignment.feature.landing.LandingPageUI
import com.example.credcomposeassignment.ui.theme.CredComposeAssignmentTheme

class MainActivity : ComponentActivity() {

    private val categoryViewModel by viewModels<CategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CredComposeAssignmentTheme {
                CentralNavigation()
            }
        }
    }

    @Composable
    private fun CentralNavigation() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Screens.LANDING_PAGE) {
            composable(Screens.LANDING_PAGE) {
                LandingPageUI(viewModel = categoryViewModel) {
                    navController.navigate(Screens.CATEGORY_PAGE)
                }
            }
            composable(Screens.CATEGORY_PAGE) {
                CategoryPageUI(
                    viewModel = categoryViewModel,
                    closeCategorySelection = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CredComposeAssignmentTheme {
        Greeting("Android")
    }
}