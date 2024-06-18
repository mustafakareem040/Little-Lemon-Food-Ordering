package com.example.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.data.Routes
import com.example.littlelemon.screens.Home
import com.example.littlelemon.screens.Onboarding
import com.example.littlelemon.screens.Profile
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data: SharedPreferences = getSharedPreferences("accountDetails", MODE_PRIVATE)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LittleLemonTheme {
                NavHost(
                    navController = navController,
                    startDestination = if (data.getBoolean("login", false)) Routes.HOME
                    else Routes.ON_BOARDING
                ) {
                    composable(Routes.ON_BOARDING) {
                        Onboarding(navController)
                    }
                    composable(Routes.HOME) {
                        Home(navController)
                    }
                    composable(Routes.PROFILE) {
                        Profile(navController = navController)
                    }
                }
            }
        }
    }
}
