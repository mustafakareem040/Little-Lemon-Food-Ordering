package com.example.littlelemon.api

import com.example.littlelemon.data.MenuItems
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

object Network {
    private val client = HttpClient(OkHttp)

    suspend fun fetchMenu(): MenuItems {
        val response: HttpResponse = client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json") {
            accept(ContentType.Application.Json)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(MenuItems.serializer(), responseBody)
    }
}

fun main() {
    runBlocking {
        println(Network.fetchMenu())
    }
}
