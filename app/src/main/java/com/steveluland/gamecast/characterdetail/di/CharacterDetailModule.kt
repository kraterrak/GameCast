package com.steveluland.gamecast.characterdetail.di

import com.steveluland.gamecast.characterdetail.CharacterDetailAPI
import com.steveluland.gamecast.characterdetail.CharacterDetailRepository
import com.steveluland.gamecast.characterdetail.ICharacterDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CharacterDetailModule {

    @Provides
    fun provideCharacterDetailRepository(characterDetailAPI: CharacterDetailAPI) : ICharacterDetailRepository {
        return CharacterDetailRepository(characterDetailAPI)
    }

    @Provides
    fun provideCharacterDetailAPI(retrofit: Retrofit): CharacterDetailAPI {
        return retrofit.create(CharacterDetailAPI::class.java)
    }
}