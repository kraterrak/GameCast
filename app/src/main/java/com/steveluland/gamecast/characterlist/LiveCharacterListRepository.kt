package com.steveluland.gamecast.characterlist

import com.steveluland.gamecast.characterlist.model.CharacterIndex

class LiveCharacterListRepository constructor(
    private val characterListAPI: CharacterListAPI,
) : CharacterListRepository {

    override suspend fun fetchCharacterList() : List<CharacterIndex> =
        characterListAPI.fetchCharacterList()
}