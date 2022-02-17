package com.steveluland.gamecast.characterdetail.uifake

import com.steveluland.gamecast.characterdetail.ICharacterDetailRepository
import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import java.lang.Exception

class FakeCharacterDetailRepository : ICharacterDetailRepository {

    private var characterDetails = mutableListOf<Pair<String, CharacterDetail>>()
    private var exception: Exception? = null

    fun setCharacterDetailWithGuid(nextGuid: String, nextCharacterDetail: CharacterDetail) {
        characterDetails.add(nextGuid to nextCharacterDetail)
    }

    fun setException(nextException: Exception) {
        exception = nextException
    }

    override suspend fun fetchCharacterDetails(guid: String) : CharacterDetail {

        exception?.let {
            throw it
        } ?:
        return characterDetails.first {
            it.first == guid
        }.second
    }
}