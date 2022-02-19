package com.steveluland.gamecast.characterlist.data

import com.steveluland.gamecast.characterlist.model.CharacterIndex

class MockCharacterListAPI : CharacterListAPI {

    override suspend fun fetchCharacterList(fieldList: String): List<CharacterIndex> {
        return listOf(FAKE_MARIO, FAKE_SONIC, FAKE_MEGA_MAN)
    }

    companion object {
        val FAKE_MARIO = CharacterIndex(name = "Mario")
        val FAKE_SONIC = CharacterIndex(name = "Sonic")
        val FAKE_MEGA_MAN = CharacterIndex(name = "Mega Man")
    }
}