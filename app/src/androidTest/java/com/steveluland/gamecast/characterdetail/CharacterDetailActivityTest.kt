package com.steveluland.gamecast.characterdetail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.PositionAssertions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.steveluland.gamecast.R
import com.steveluland.gamecast.characterdetail.di.CharacterDetailModule
import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(CharacterDetailModule::class)
class CharacterDetailActivityTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var activityRule = ActivityScenarioRule(CharacterDetailActivity::class.java)

    @BindValue
    val fakeCharacterDetailRepository = mock<ICharacterDetailRepository> {
        onBlocking { fetchCharacterDetails(any()) } doReturn CharacterDetail(name = FIRST_NAME, description = FIRST_DESCRIPTION)
    }

    @Test
    fun characterDetailAvailable_characterDetailShown() {

        val characterNameView = withId(R.id.character_name)
        val characterDescriptionView = withId(R.id.character_description)

        onView(characterNameView).run {
            check(matches(isDisplayed()))
            check(matches(withText(FIRST_NAME)))
            check(isCompletelyAbove(characterDescriptionView))
        }

        onView(characterDescriptionView).run {
            check(matches(isDisplayed()))
            check(matches(withText(FIRST_DESCRIPTION)))
            check(isCompletelyBelow(characterNameView))
        }
    }

    companion object {
        private const val FIRST_NAME = "Mario"
        private const val FIRST_DESCRIPTION = "Hero of the Mushroom Kingdom, he can normally be found trying to save Princess Peach"
    }
}