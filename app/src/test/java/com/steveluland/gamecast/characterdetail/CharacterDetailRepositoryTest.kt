package com.steveluland.gamecast.characterdetail

import com.google.common.truth.Truth.assertThat
import com.steveluland.gamecast.characterdetail.fake.FakeCharacterDetailAPI
import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterDetailRepositoryTest {

    lateinit var sut: CharacterDetailRepository
    lateinit var fakeCharacterDetailAPI: FakeCharacterDetailAPI

    @Before
    fun setUp() {
        fakeCharacterDetailAPI = FakeCharacterDetailAPI()
        sut = CharacterDetailRepository(fakeCharacterDetailAPI)
    }

    @Test
    fun `GIVEN character details are available WHEN character details are requested THEN character details are returned`() = runTest {
        fakeCharacterDetailAPI.setCharacterDetailWithGuid(FIRST_GUID, CharacterDetail(name = FIRST_NAME))
        val characterDetail = sut.fetchCharacterDetails(FIRST_GUID)
        assertThat(characterDetail.name).isEqualTo(FIRST_NAME)
    }

    companion object {
        const val FIRST_GUID = "1"
        const val FIRST_NAME = "Mario"
    }
}