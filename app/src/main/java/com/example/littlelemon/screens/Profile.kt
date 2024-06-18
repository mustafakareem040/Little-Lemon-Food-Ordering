package com.example.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.edit
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemon.common.PersonalAccount
import com.example.littlelemon.data.Account
import com.example.littlelemon.data.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "Profile", color = MaterialTheme.colorScheme.onPrimary) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, "Arrow Back")
                }
            },
        )
        Image(
            painter = painterResource(R.drawable.logo), contentDescription = "Little lemon logo",
            modifier = Modifier
                .fillMaxWidth()
                .scale(0.5f),
            contentScale = ContentScale.Crop
        )

        PersonalAccount(submitContent = "Logout") {
            context.getSharedPreferences("accountDetails", Context.MODE_PRIVATE).edit(true) {
                clear()
            }
            Account.clear()
            navController.navigate(Routes.ON_BOARDING) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }
}
