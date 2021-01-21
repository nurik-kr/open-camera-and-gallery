package kg.nurik.cameraimage.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.nurik.cameraimage.data.model.ProfileModel
import kg.nurik.cameraimage.data.repositories.UserRepository
import kg.nurik.cameraimage.utils.toImageRequestBody
import kg.nurik.cameraimage.utils.toJsonRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    val userData = MutableLiveData<ProfileModel>()

    private fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = repository.loadUserProfile()
                userData.postValue(result.body())
                Log.d("asdsadasd", "asdasdasdasd")
            }.onFailure {
                Log.d("asdsadasd", "asdasdasdasd")
            }
        }
    }

    fun authUser() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = repository.login("rojsasha@gmail.com", "fifa11alex")
                if (result.isSuccessful)
                    loadUser()
            }.onFailure {
                it.message?.let { it1 -> Log.e("error", it1) }
            }
        }
    }

    fun updateUserWithPhoto(file: File) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = userData.value?.toJsonRequestBody()?.let {
                    repository.updateUserWithImage(
                        body = it,
                        avatar = file.toImageRequestBody(AVATAR)
                    )
                }
                Log.d("asdasdasdasd", "asdasdasdasdasd")
                if (result != null) {
                    if (result.isSuccessful)
                        userData.postValue(result.body())
                }
            }.onFailure {
                Log.d("asdasdasdasd", "asdasdasdasdasd")
            }
        }
    }

    companion object {
        private const val AVATAR = "avatar"
    }
}