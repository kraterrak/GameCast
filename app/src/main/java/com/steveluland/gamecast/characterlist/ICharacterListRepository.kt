package com.steveluland.gamecast.characterlist

import com.steveluland.gamecast.characterlist.model.CharacterIndex

interface ICharacterListRepository {

    suspend fun fetchCharacterList() : List<CharacterIndex>
}