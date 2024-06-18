package com.example.littlelemon.common

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.data.Account
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun PersonalAccount(submitContent: String, onSubmit: () -> Unit) {
    val context = LocalContext.current
    var readOnly = remember { false }
    LaunchedEffect(Unit) {
        val sharedPreference = context.getSharedPreferences("accountDetails", Context.MODE_PRIVATE)
        if (sharedPreference.getBoolean("login", false)) {
            sharedPreference.getString("firstName", "")
            sharedPreference.getString("lastName", "")
            sharedPreference.getString("email", "")
            readOnly = true
        }
    }
    Column(Modifier.fillMaxSize()) {
        Text(text = "Personal Information",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp))
        TextEdit("First name",
            Account.firstName,
            Account::editFirstName,
            Account::onNameError,
            readOnly)
        TextEdit("Last name",
            Account.lastName,
            Account::editLastName,
            Account::onNameError,
            readOnly)
        TextEdit("Email",
            Account.email,
            Account::editEmail,
            Account::onEmailError,
            readOnly)
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        Submit(text = submitContent, onSubmit)
    }
}

@Preview(showBackground = true)
@Composable
fun AccountPreview() {
    LittleLemonTheme {
        PersonalAccount("Register") {}
    }
}