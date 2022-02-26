package com.steveluland.gamecast.characterdetail

import com.steveluland.gamecast.characterdetail.model.CharacterDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailAPI {

    @GET("character/{guid}")
    suspend fun fetchCharacterDetail(@Path("guid") guid: String) : CharacterDetail
}