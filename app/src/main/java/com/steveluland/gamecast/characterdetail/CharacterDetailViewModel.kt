package com.steveluland.gamecast.characterdetail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailRepository: ICharacterDetailRepository,
) : ViewModel() {

    private val _characterDetailState = MutableStateFlow<CharacterDetailState>(
        CharacterDetailState.Success(CharacterDetail())
    )
    val characterDetailState: StateFlow<CharacterDetailState> = _characterDetailState

    fun getCharacterDetails(guid: String?) {
        guid?.let {
            viewModelScope.launch {
                try {
                    val characterDetail = characterDetailRepository.fetchCharacterDetails(guid)
                    _characterDetailState.value = CharacterDetailState.Success(characterDetail)
                } catch (e: Exception) {
                    Log.e(TAG, "getCharacterDetails: ${e.stackTraceToString()}")
                    _characterDetailState.value = CharacterDetailState.Error
                }
            }
        } ?: guid.run { _characterDetailState.value = CharacterDetailState.Error }
    }
}

sealed class CharacterDetailState {
    data class Success(val characterDetail: CharacterDetail): CharacterDetailState()
    object Error: CharacterDetailState()
}