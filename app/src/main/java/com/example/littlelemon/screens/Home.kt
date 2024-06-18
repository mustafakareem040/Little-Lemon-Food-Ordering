package com.example.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemon.api.Network
import com.example.littlelemon.common.FoodItem
import com.example.littlelemon.common.MealButton
import com.example.littlelemon.data.MenuItem
import com.example.littlelemon.data.Routes
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home(navController: NavController) {
    val (query, setQuery) = remember { mutableStateOf("") }
    var foodMenu by remember { mutableStateOf(listOf<MenuItem>()) }
    var filteredMenu by remember { mutableStateOf(listOf<MenuItem>()) }
    val (selectedCategory, setSelectedCategory) = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        coroutineScope {
            launch {
                foodMenu = Network.fetchMenu().menu
                filteredMenu = foodMenu
            }
        }
    }

    LaunchedEffect(query, selectedCategory) {
        filteredMenu = foodMenu.filter {
            it.title.contains(query, ignoreCase = true) &&
                    (selectedCategory.isEmpty() || it.category == selectedCategory)
        }
    }

    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Little lemon logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(0.5f),
                contentScale = ContentScale.Crop
            )
            IconButton(onClick = { navController.navigate(Routes.PROFILE) },
                modifier = Modifier.align(Alignment.CenterEnd)) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .scale(1.5f)
                )
            }
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(10.dp)
        ) {
            Text(
                text = "Little Lemon",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            Text(
                text = "Chicago",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 30.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.dessert),
                    contentDescription = "Dessert",
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .align(Alignment.TopEnd)
                        .padding(bottom = 40.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
            }
            TextField(value = query, onValueChange = setQuery, leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }, placeholder = { Text(text = "Enter search phrase") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .padding(vertical = 10.dp)
            )
        }
        Column(modifier = Modifier.padding(start = 5.dp, top = 10.dp)) {
            Text("ORDER FOR DELIVERY!", style = MaterialTheme.typography.headlineSmall)
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(1) {
                    MealButton(text = "All") {
                        setSelectedCategory("")
                    }
                    MealButton(text = "Starters") {
                        setSelectedCategory("starters")
                    }
                    MealButton(text = "Mains") {
                        setSelectedCategory("mains")
                    }
                    MealButton(text = "Desserts") {
                        setSelectedCategory("desserts")
                    }
                    MealButton(text = "Drinks") {
                        setSelectedCategory("drinks")
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.outline)
        )
        LazyColumn {
            items(filteredMenu.size) { index ->
                FoodItem(menuItem = filteredMenu[index])
            }
        }
    }
}