package com.steveluland.gamecast.characterlist

import com.steveluland.gamecast.characterlist.model.CharacterIndex

class FakeCharacterListAPI : CharacterListAPI {

    private var characterList : List<CharacterIndex> = emptyList()

    fun setCharacterList(newCharacterList: List<CharacterIndex>) {
        characterList = newCharacterList
    }

    override suspend fun fetchCharacterList(): List<CharacterIndex> = characterList
}