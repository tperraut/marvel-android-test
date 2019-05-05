package rocks.flawless.marveltestapp.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import rocks.flawless.marveltestapp.R
import rocks.flawless.marveltestapp.api.retrofit.Retrofit
import rocks.flawless.marveltestapp.api.retrofit.services.CharacterService
import java.io.IOException

object Api {
    private val retrofit = Retrofit.createRetrofit()

    val characterService: CharacterService by lazy {
        retrofit.create(CharacterService::class.java)
    }

    suspend fun <T> safeCall(call: suspend () -> Response<T>?): T = withContext(Dispatchers.IO) {
        try {
            val response = call()
            if (response == null || !response.isSuccessful || response.body() == null) {
                throw ErrorException(R.string.generic_error_unknown)
            } else {
                response.body()!!
            }
        } catch (e: IOException) {
            throw ErrorException(R.string.generic_error_connection_timeout, e)
        } catch (e: HttpException) {
            throw ErrorException(R.string.generic_error_connection_timeout, e)
        }
    }
}