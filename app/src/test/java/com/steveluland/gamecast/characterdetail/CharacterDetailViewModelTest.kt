package com.steveluland.gamecast.characterdetail

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.steveluland.gamecast.characterdetail.fake.FakeCharacterDetailRepository
import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import com.steveluland.gamecast.rules.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var sut: CharacterDetailViewModel
    private lateinit var fakeCharacterDetailRepository: FakeCharacterDetailRepository

    @Before
    fun setUp() {
        fakeCharacterDetailRepository = FakeCharacterDetailRepository()
        sut = CharacterDetailViewModel(fakeCharacterDetailRepository)
    }

    @Test
    fun `GIVEN character details are available from the repository WHEN character details are requested successfully THEN the view is given the character details`() = runTest {
        fakeCharacterDetailRepository.setCharacterDetailWithGuid(FIRST_GUID, CharacterDetail(name = FIRST_NAME))
        sut.getCharacterDetails(FIRST_GUID)

        sut.characterDetailState.test {
            assertThat(awaitItem()).isEqualTo(
                CharacterDetailState.Success(CharacterDetail(name = FIRST_NAME))
            )
        }
    }

    @Test
    fun `GIVEN character details from the repository will fail WHEN character details call fails THEN an error is shown on the view`() = runTest {
        fakeCharacterDetailRepository.setException(Exception())
        sut.getCharacterDetails(FIRST_GUID)

        sut.characterDetailState.test {
            assertThat(awaitItem()).isEqualTo(CharacterDetailState.Error)
        }
    }

    companion object {
        private const val FIRST_GUID = "1"
        private const val FIRST_NAME = "Mario"
    }
}