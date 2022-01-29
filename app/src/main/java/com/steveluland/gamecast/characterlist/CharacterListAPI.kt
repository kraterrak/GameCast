package com.steveluland.gamecast.characterlist

import com.steveluland.gamecast.characterlist.model.CharacterIndex

interface CharacterListAPI {

    suspend fun fetchCharacterList() : List<CharacterIndex>
}