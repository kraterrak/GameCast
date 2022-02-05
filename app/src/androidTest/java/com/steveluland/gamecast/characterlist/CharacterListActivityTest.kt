package com.steveluland.gamecast.characterlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.steveluland.gamecast.characterlist.model.CharacterIndex
import com.steveluland.gamecast.characterlist.uifake.FakeCharacterListRepository
import com.steveluland.gamecast.ui.theme.GameCastTheme
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CharacterListActivityTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<CharacterListActivity>()

    @BindValue
    val fakeCharacterListRepository = FakeCharacterListRepository()

    @Test
    fun contactListAvailable_characterListShown() {
        fakeCharacterListRepository.setCharacterList(listOf(FAKE_MARIO, FAKE_MEGA_MAN, FAKE_SONIC))

        composeTestRule.setContent {
            GameCastTheme {
                CharacterListScreen()
            }
        }

        composeTestRule.onNodeWithText("Mario").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sonic").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mega Man").assertIsDisplayed()
    }

    companion object {
        val FAKE_MARIO = CharacterIndex(name = "Mario")
        val FAKE_SONIC = CharacterIndex(name = "Sonic")
        val FAKE_MEGA_MAN = CharacterIndex(name = "Mega Man")
    }
}