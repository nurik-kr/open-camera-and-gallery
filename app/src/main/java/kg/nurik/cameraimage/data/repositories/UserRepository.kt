package kg.nurik.cameraimage.data.repositories

import kg.nurik.cameraimage.data.interactor.UserInteractor
import kg.nurik.cameraimage.data.model.ProfileModel
import kg.nurik.cameraimage.data.model.TokenModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface UserRepository {
    suspend fun login(userName: String, password: String): Response<TokenModel>
    suspend fun loadUserProfile(): Response<ProfileModel?>
    suspend fun updateUserWithImage(
        body: RequestBody,
        avatar: MultipartBody.Part
    ): Response<ProfileModel?>
}

class UserRepositoryImpl(private val network: UserInteractor) : UserRepository {

    override suspend fun login(userName: String, password: String): Response<TokenModel> {
        return network.login(userName, password)
    }

    override suspend fun loadUserProfile(): Response<ProfileModel?> {
        return network.loadUserProfile()
    }

    override suspend fun updateUserWithImage(
        body: RequestBody,
        avatar: MultipartBody.Part
    ): Response<ProfileModel?> {
        return network.updateUserWithImage(
            body = body, avatar = avatar)
    }
}