package com.steveluland.gamecast.characterlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.steveluland.gamecast.characterlist.model.CharacterIndex
import com.steveluland.gamecast.ui.error.ErrorScreen
import com.steveluland.gamecast.ui.theme.GameCastTheme

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onCharacterItemClicked: (guid: String) -> Unit,
) {
    val uiState = viewModel.characterListState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.refreshCharacterList()
    }

    when(uiState) {
        is CharacterListState.Success -> CharacterList(uiState.characterList, onCharacterItemClicked)
        CharacterListState.Error -> ErrorScreen(
            text = "We couldn't fetch the list of characters right now, please check your internet connection and try again."
        ) { viewModel.refreshCharacterList() }
    }
}

@Composable
fun CharacterList(
    characterList: List<CharacterIndex>,
    onCharacterItemClicked: (guid: String) -> Unit,
) {
    LazyColumn {
        characterList.forEach {
            item {
                CharacterListItem(it, onCharacterItemClicked)
            }
        }
    }
}

@Composable
fun CharacterListItem(
    characterIndex: CharacterIndex,
    onCharacterItemClicked: (guid: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onCharacterItemClicked(characterIndex.guid) }
    ) {
        Text(
            text = characterIndex.name,
            fontSize = 22.sp,
        )
    }
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
        ) {}
    }
}