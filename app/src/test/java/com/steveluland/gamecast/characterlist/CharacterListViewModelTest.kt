package com.steveluland.gamecast.characterlist

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.steveluland.gamecast.characterlist.fake.FakeCharacterListRepository
import com.steveluland.gamecast.characterlist.model.CharacterIndex
import com.steveluland.gamecast.rules.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class CharacterListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeCharacterListRepository: FakeCharacterListRepository
    private lateinit var sut: CharacterListViewModel

    @Before
    fun setUp() {
        fakeCharacterListRepository = FakeCharacterListRepository()
        sut = CharacterListViewModel(fakeCharacterListRepository)
    }

    @Test
    fun `GIVEN character list is available from repository WHEN character list is refreshed successfully THEN character list is returned to the UI`() = runBlocking {
        val expectedCharacterList = listOf(FAKE_MARIO, FAKE_SONIC, FAKE_MEGA_MAN)
        fakeCharacterListRepository.setCharacterList(expectedCharacterList)

        sut.refreshCharacterList()

        sut.characterListState.test {
            assertThat(awaitItem()).isEqualTo(CharacterListState.Success(expectedCharacterList))
        }
    }

    @Test
    fun `GIVEN character list from repository will fail WHEN character list is refreshed unsuccessfully THEN error is sent to UI`() = runBlocking {
        fakeCharacterListRepository.setException(Exception())

        sut.refreshCharacterList()

        sut.characterListState.test {
            assertThat(awaitItem()).isEqualTo(CharacterListState.Error)
        }
    }

    companion object {
        val FAKE_MARIO = CharacterIndex(name = "Mario")
        val FAKE_SONIC = CharacterIndex(name = "Sonic")
        val FAKE_MEGA_MAN = CharacterIndex(name = "Mega Man")
    }
}