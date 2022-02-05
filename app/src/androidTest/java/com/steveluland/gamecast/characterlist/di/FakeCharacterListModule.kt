package com.steveluland.gamecast.characterlist.di

import com.steveluland.gamecast.characterlist.*
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

//@Module
//@TestInstallIn(
//    components = [ViewModelComponent::class],
//    replaces = [CharacterListModule::class]
//)
//class FakeCharacterListModule {
//
//    @Provides
//    fun provideCharacterListRepository() : ICharacterListRepository = FakeUICharacterListRepository()
//}