package com.steveluland.gamecast.characterdetail

import android.text.Html
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import com.steveluland.gamecast.ui.error.ErrorScreen
import com.steveluland.gamecast.ui.theme.GameCastTheme

@Composable
fun CharacterDetailScreen(guid: String?, viewModel: CharacterDetailViewModel = hiltViewModel()) {
    val uiState = viewModel.characterDetailState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getCharacterDetails(guid)
    }

    when(uiState) {
        is CharacterDetailState.Success -> CharacterDetail(uiState.characterDetail)
        CharacterDetailState.Error -> ErrorScreen(
            text = "We couldn't fetch your character's information right now, please check your internet connection and try again."
        ) { viewModel.getCharacterDetails(guid) }
    }
}

@Composable
fun CharacterDetail(characterDetail: CharacterDetail) {
    Column (
        modifier = Modifier
            .padding(
                horizontal = 16.dp
            )
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = characterDetail.name,
            fontSize = 22.sp,
            modifier = Modifier.padding(
                vertical = 16.dp
            )
        )
        Text(
            text = HtmlCompat.fromHtml(characterDetail.description, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterDetailPreview() {
    GameCastTheme {
        CharacterDetail(
            CharacterDetail(
                name = "Mario",
                description = "Hero of the Mushroom Kingdom, he can normally be found trying to save Princess Peach"
            )
        )
    }
}