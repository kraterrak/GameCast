package com.steveluland.gamecast.characterdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import com.steveluland.gamecast.ui.theme.GameCastTheme

@Composable
fun CharacterDetailScreen(guid: String, viewModel: CharacterDetailViewModel = hiltViewModel()) {
    val uiState = viewModel.characterDetailState.collectAsState().value

    viewModel.getCharacterDetails(guid)

    when(uiState) {
        is CharacterDetailState.Success -> CharacterDetail(uiState.characterDetail)
        CharacterDetailState.Error -> {}
    }
}

@Composable
fun CharacterDetail(characterDetail: CharacterDetail) {
    Column (
        modifier = Modifier.padding(
            horizontal = 16.dp
        )
    ) {
        Text(
            text = characterDetail.name,
            fontSize = 22.sp,
            modifier = Modifier.padding(
                vertical = 16.dp
            )
        )
        Text(
            text = characterDetail.description
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