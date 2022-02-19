package com.steveluland.gamecast.characterlist.di

import com.steveluland.gamecast.characterlist.data.CharacterListAPI
import com.steveluland.gamecast.characterlist.data.CharacterListRepository
import com.steveluland.gamecast.characterlist.data.ICharacterListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CharacterListModule {

    @Provides
    fun provideCharacterListRepository(api: CharacterListAPI) : ICharacterListRepository =
        CharacterListRepository(api)

    @Provides
    fun provideCharacterListAPI(retrofit: Retrofit) : CharacterListAPI {
        return retrofit.create(CharacterListAPI::class.java)
    }
}