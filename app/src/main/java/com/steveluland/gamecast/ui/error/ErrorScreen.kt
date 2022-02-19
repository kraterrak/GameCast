package com.steveluland.gamecast.ui.error

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.steveluland.gamecast.ui.theme.GameCastTheme

@Composable
fun ErrorScreen(
    text: String = "An error has occurred, please check your internet connection and try again.",
    onRefresh: () -> Unit
) {
    Column {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Try again",
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { onRefresh() }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorPreview() {
    GameCastTheme {
        ErrorScreen {}
    }
}