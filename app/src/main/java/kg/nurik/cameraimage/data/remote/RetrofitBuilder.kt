package kg.nurik.cameraimage.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object  RetrofitBuilder {
    private const val TIME_INTERVAL = 15L
    fun initRetrofit(baseUrl: String): RetrofitService { //s koina peredadim baseUrl
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(initOkHttpClient(baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    private fun initOkHttpClient(baseUrl: String): OkHttpClient {
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(HeadersInterceptor()) //тут модиф запроса добавляем
            .authenticator(TokenAuthenticator(authApi(baseUrl)))
            .writeTimeout(TIME_INTERVAL, TimeUnit.SECONDS)
            .readTimeout(TIME_INTERVAL, TimeUnit.SECONDS)
            .connectTimeout(TIME_INTERVAL, TimeUnit.SECONDS)
        okHttp.addInterceptor(providerLogginingInterceptor())
        return okHttp.build()
    }

    private fun authApi(baseUrl: String): AuthApi { //сервис для авторизации
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client( // для того чтобы логи печатать
                OkHttpClient.Builder()
                    .addInterceptor(providerLogginingInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    private fun providerLogginingInterceptor(): HttpLoggingInterceptor { //печатать все запросы
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
}