package com.example.littlelemon.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.regex.Pattern

object Account {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    private val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )
    fun editFirstName(newName: String) {
        firstName = newName
    }
    fun editLastName(newName: String) {
        lastName = newName
    }
    fun editEmail(newEmail: String) {
        email = newEmail
    }
    fun onNameError(name: String): Boolean {
        return name.isEmpty() || name.length > 128
    }
    fun onEmailError(email: String): Boolean {
        return !emailPattern.matcher(email).matches()
    }
    fun allValid(): Boolean {
        return !(onNameError(firstName) || onNameError(lastName) || onEmailError(email))
    }
    fun clear() {
        firstName = ""
        lastName = ""
        email = ""
    }
}
