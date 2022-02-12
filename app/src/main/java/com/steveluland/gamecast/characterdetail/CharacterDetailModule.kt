package com.steveluland.gamecast.characterdetail

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CharacterDetailModule {

    @Provides
    fun provideCharacterDetailAPI(retrofit: Retrofit): CharacterDetailAPI {
        return retrofit.create(CharacterDetailAPI::class.java)
    }
}