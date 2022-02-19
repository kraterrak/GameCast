package com.steveluland.gamecast.characterlist.data

import com.steveluland.gamecast.characterlist.model.CharacterIndex
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterListAPI {

    @GET("characters")
    suspend fun fetchCharacterList(@Query("field_list") fieldList: String = "guid,name") : List<CharacterIndex>
}