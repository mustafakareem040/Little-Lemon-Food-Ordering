package com.example.littlelemon.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextEdit(label: String, value: String,
             onValueChange: (String) -> Unit,
             isError: (String) -> Boolean, readOnly: Boolean) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError(value),
        singleLine = true,
        readOnly = readOnly,
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp)
    )
}