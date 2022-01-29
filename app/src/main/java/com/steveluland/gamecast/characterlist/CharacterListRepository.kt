package com.steveluland.gamecast.characterlist

import com.steveluland.gamecast.characterlist.model.CharacterIndex

interface CharacterListRepository {

    suspend fun fetchCharacterList() : List<CharacterIndex>
}