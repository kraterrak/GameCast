package com.steveluland.gamecast.characterdetail.fake

import com.steveluland.gamecast.characterdetail.CharacterDetailAPI
import com.steveluland.gamecast.characterdetail.model.CharacterDetail

class FakeCharacterDetailAPI : CharacterDetailAPI {

    private var characterDetails = mutableListOf<Pair<String, CharacterDetail>>()

    fun setCharacterDetailWithGuid(nextGuid: String, nextCharacterDetail: CharacterDetail) {
          characterDetails.add(nextGuid to nextCharacterDetail)
    }

    override fun fetchCharacterDetail(guid: String) = characterDetails.first {
        it.first == guid
    }.second
}