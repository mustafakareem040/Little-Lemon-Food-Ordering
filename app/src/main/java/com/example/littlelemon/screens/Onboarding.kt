package com.example.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemon.common.PersonalAccount
import com.example.littlelemon.data.Account
import com.example.littlelemon.data.Routes

@Composable
fun Onboarding(navController: NavController) {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.logo), contentDescription = "Little lemon logo",
            modifier = Modifier
                .fillMaxWidth()
                .scale(0.5f),
            contentScale = ContentScale.Crop
        )
        Text(text = "Let's get to know you",
            style= MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
                .padding(40.dp))
        PersonalAccount(submitContent = "Register") {
            if (Account.allValid()) {
                context.getSharedPreferences("accountDetails", Context.MODE_PRIVATE)
                    .edit(commit = true) {
                        putBoolean("login", true)
                        putString("firstName", Account.firstName)
                        putString("lastName", Account.lastName)
                        putString("email", Account.email)
                    }
                navController.navigate(Routes.HOME)
            }
        }
    }
}