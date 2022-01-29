package com.steveluland.gamecast.characterlist

import com.google.common.truth.Truth.assertThat
import com.steveluland.gamecast.characterlist.model.CharacterIndex
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterListRepositoryTest {

    private lateinit var fakeCharacterListAPI: FakeCharacterListAPI
    private lateinit var sut: CharacterListRepository

    @Before
    fun setUp() {
        fakeCharacterListAPI = FakeCharacterListAPI()
        sut = CharacterListRepository(fakeCharacterListAPI)
    }

    @Test
    fun `GIVEN a character list is available from API WHEN the character list is retrieved from the API THEN the correct character list is retrieved`() = runBlocking {
        fakeCharacterListAPI.setCharacterList(listOf(FAKE_MARIO, FAKE_SONIC, FAKE_MEGA_MAN))
        val characterList = sut.fetchCharacterList()
        assertThat(characterList).containsExactly(FAKE_MARIO, FAKE_SONIC, FAKE_MEGA_MAN).inOrder()
    }

    companion object {
        val FAKE_MARIO = CharacterIndex(name = "Mario")
        val FAKE_SONIC = CharacterIndex(name = "Sonic")
        val FAKE_MEGA_MAN = CharacterIndex(name = "Mega Man")
    }
}