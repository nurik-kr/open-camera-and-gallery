package kg.nurik.cameraimage.data.remote


import kg.nurik.cameraimage.data.local.PrefsHelper
import kg.nurik.cameraimage.data.model.TokenModel
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val authApi: AuthApi) :
    Authenticator { //вдруг токен сдохнет и тут мы обновляем токен в бэке и отдельный ретрофит
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == 401) {
            PrefsHelper.saveToken("") //обнуляем токен
            val result = refreshToken() //res выпол функции
            if (result.isSuccessful && result.body() != null) {
                val token = result.body()?.token ?: "" //сохр токен в PrefsHelper
                token.let { PrefsHelper.saveToken(it) } //обновляем токен
                return response.request.newBuilder() //repeat пред запрос
                    .addHeader("token", PrefsHelper.getToken()) //перезаписываем токен
                    .build()
            } else {
                return null
            }
        }
        return null
    }

    private fun refreshToken(): retrofit2.Response<TokenModel> {
        return runBlocking {
            val map = mapOf(
                "email" to "rojsasha@gmail.com",
                "password" to "fifa11alex"
            )
            val result = authApi.login(map)
            return@runBlocking result
        }
    }
}