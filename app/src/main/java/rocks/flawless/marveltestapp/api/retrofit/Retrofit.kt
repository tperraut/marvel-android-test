package rocks.flawless.marveltestapp.api.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rocks.flawless.marveltestapp.BuildConfig
import rocks.flawless.marveltestapp.api.retrofit.interceptors.AuthInterceptor
import java.util.concurrent.TimeUnit

object Retrofit {
    fun createRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .disableHtmlEscaping()
            .create()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(createHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun createHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor())
            .addInterceptor(logging)
            .build()
    }
}