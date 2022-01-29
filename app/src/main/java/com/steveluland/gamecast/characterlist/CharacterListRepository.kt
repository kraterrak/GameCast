package com.steveluland.gamecast.characterlist

import com.steveluland.gamecast.characterlist.model.CharacterIndex

class CharacterListRepository constructor(
    private val characterListAPI: CharacterListAPI,
) : ICharacterListRepository {

    override suspend fun fetchCharacterList() : List<CharacterIndex> =
        characterListAPI.fetchCharacterList()
}