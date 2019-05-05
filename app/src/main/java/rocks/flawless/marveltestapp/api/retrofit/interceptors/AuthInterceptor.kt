package rocks.flawless.marveltestapp.api.retrofit.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import rocks.flawless.marveltestapp.BuildConfig
import rocks.flawless.marveltestapp.helpers.md5

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis().toString()
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_PUBLIC_KEY)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", "$ts${BuildConfig.API_PRIVATE_KEY}${BuildConfig.API_PUBLIC_KEY}".md5())
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}