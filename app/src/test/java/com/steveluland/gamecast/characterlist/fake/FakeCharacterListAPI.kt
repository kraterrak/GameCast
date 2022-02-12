package com.steveluland.gamecast.characterlist.fake

import com.steveluland.gamecast.characterlist.CharacterListAPI
import com.steveluland.gamecast.characterlist.model.CharacterIndex
import com.steveluland.gamecast.core.network.GBResponse

class FakeCharacterListAPI : CharacterListAPI {

    private var characterList : List<CharacterIndex> = emptyList()

    fun setCharacterList(newCharacterList: List<CharacterIndex>) {
        characterList = newCharacterList
    }

    override suspend fun fetchCharacterList(fieldList: String): List<CharacterIndex> = characterList
}