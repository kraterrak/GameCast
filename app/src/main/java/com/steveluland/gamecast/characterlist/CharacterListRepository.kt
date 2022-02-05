package com.steveluland.gamecast.characterlist

import com.steveluland.gamecast.characterlist.model.CharacterIndex
import javax.inject.Inject

class CharacterListRepository @Inject constructor(
    private val characterListAPI: CharacterListAPI,
) : ICharacterListRepository {

    override suspend fun fetchCharacterList() : List<CharacterIndex> =
        characterListAPI.fetchCharacterList().results
}