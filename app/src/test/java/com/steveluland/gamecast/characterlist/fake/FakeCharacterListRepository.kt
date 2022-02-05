package com.steveluland.gamecast.characterlist.fake

import com.steveluland.gamecast.characterlist.ICharacterListRepository
import com.steveluland.gamecast.characterlist.model.CharacterIndex
import java.lang.Exception

class FakeCharacterListRepository : ICharacterListRepository {

    private var characterList : List<CharacterIndex> = emptyList()
    private var exception : Exception? = null

    fun setCharacterList(nextCharacterList: List<CharacterIndex>) {
        characterList = nextCharacterList
    }

    fun setException(nextException: Exception?) {
        exception = nextException
    }

    override suspend fun fetchCharacterList(): List<CharacterIndex> =
        exception?.let {
            throw it
        } ?: characterList
}