package com.steveluland.gamecast.characterlist.di

import com.steveluland.gamecast.characterlist.CharacterListAPI
import com.steveluland.gamecast.characterlist.CharacterListRepository
import com.steveluland.gamecast.characterlist.ICharacterListRepository
import com.steveluland.gamecast.characterlist.MockCharacterListAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class CharacterListModule {

    @Provides
    fun provideCharacterListRepository(api: CharacterListAPI) : ICharacterListRepository =
        CharacterListRepository(api)

    @Provides
    fun provideCharacterListAPI() : CharacterListAPI = MockCharacterListAPI()
}