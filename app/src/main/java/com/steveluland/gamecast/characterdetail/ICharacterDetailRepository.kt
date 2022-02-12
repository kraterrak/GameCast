package com.steveluland.gamecast.characterdetail

import com.steveluland.gamecast.characterdetail.model.CharacterDetail

interface ICharacterDetailRepository {

    suspend fun fetchCharacterDetails(guid: String) : CharacterDetail
}