package com.steveluland.gamecast.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steveluland.gamecast.characterlist.CharacterListState.*
import com.steveluland.gamecast.characterlist.model.CharacterIndex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class CharacterListViewModel constructor(
    private val characterListRepository: ICharacterListRepository,
) : ViewModel() {

    private val _characterListState = MutableStateFlow<CharacterListState>(Success(emptyList()))
    val characterListState: StateFlow<CharacterListState> = _characterListState

    fun refreshCharacterList() {
        viewModelScope.launch {
            try {
                val characterList = characterListRepository.fetchCharacterList()
                _characterListState.value = Success(characterList)
            } catch (e: Exception) {
                _characterListState.value = Error
            }
        }
    }
}

sealed class CharacterListState {
    data class Success(val characterList: List<CharacterIndex>): CharacterListState()
    object Error: CharacterListState()
}
