package kg.nurik.cameraimage.data.remote

import kg.nurik.cameraimage.data.local.PrefsHelper
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor { // модификация запроса
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PrefsHelper.getToken() //получаем токен из префа
        val request = chain.request().newBuilder() //новый запрос
        if (token.isNotEmpty())
            request.addHeader("token", token)

        return chain.proceed(request.build())
    }
}