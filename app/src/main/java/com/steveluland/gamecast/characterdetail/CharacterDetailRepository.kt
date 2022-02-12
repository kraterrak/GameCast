package com.steveluland.gamecast.characterdetail

import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import javax.inject.Inject

class CharacterDetailRepository @Inject constructor(
    private val characterDetailAPI: CharacterDetailAPI,
) : ICharacterDetailRepository {

    override suspend fun fetchCharacterDetails(guid: String): CharacterDetail {
        return characterDetailAPI.fetchCharacterDetail(guid)
    }
}