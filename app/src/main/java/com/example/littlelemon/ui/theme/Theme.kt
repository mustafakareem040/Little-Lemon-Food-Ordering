package com.example.littlelemon.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = Yellow,
    background = White,
    onBackground = Black,
    surface = White,
    tertiary = LightGray,
    onTertiary = Black,
    onPrimary = Black,
    primaryContainer = DeepAquamarine,
    onPrimaryContainer = WhiteSmoke,
)

@Composable
fun LittleLemonTheme(
    content: @Composable () -> Unit
) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            shapes = Shapes(
                small = RoundedCornerShape(2.dp),
                medium = RoundedCornerShape(4.dp),
                large = RoundedCornerShape(6.dp)
            ),
            content = content
        )
}
