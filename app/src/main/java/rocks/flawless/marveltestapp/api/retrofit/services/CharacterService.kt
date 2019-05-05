package rocks.flawless.marveltestapp.api.retrofit.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rocks.flawless.marveltestapp.api.retrofit.models.Hero
import rocks.flawless.marveltestapp.api.retrofit.models.responses.DataResponse

interface CharacterService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("nameStartsWith", encoded = true) name: String? = null,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<DataResponse<Hero>>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") id: Int
    ): Response<DataResponse<Hero>>
}