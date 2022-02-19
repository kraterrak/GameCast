package com.steveluland.gamecast.characterlist.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.steveluland.gamecast.characterlist.model.CharacterIndex
import com.steveluland.gamecast.ui.theme.GameCastTheme

@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel = hiltViewModel()) {

    val uiState = viewModel.characterListState.collectAsState().value

    viewModel.refreshCharacterList()

    when(uiState) {
        is CharacterListState.Success -> CharacterList(uiState.characterList)
        CharacterListState.Error -> ErrorScreen()
    }
}

@Composable
fun CharacterList(characterList: List<CharacterIndex>) {
    LazyColumn {
        characterList.forEach {
            item {
                Text(text = it.name)
            }
        }
    }
}

@Composable
fun ErrorScreen() {
    Text(text = "We couldn't fetch the list of characters right now, please check your internet connection and try again.")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessPreview() {
    GameCastTheme {
        CharacterList(
            listOf(
                CharacterIndex(name = "Bulbasaur"),
                CharacterIndex(name = "Charmander"),
                CharacterIndex(name = "Squirtle")
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorPreview() {
    GameCastTheme {
        ErrorScreen()
    }
}