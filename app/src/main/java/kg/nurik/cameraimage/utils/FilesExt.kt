package kg.nurik.cameraimage.utils

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

fun File.toImageRequestBody(name: String): MultipartBody.Part {
    return asRequestBody("image/*".toMediaTypeOrNull()).let {
        MultipartBody.Part.createFormData(
            name,
            this.name,
            it
        )
    }
}

fun Any.toJsonRequestBody(): RequestBody {
    return Gson().toJson(this)
        .toRequestBody("application/json".toMediaTypeOrNull())
}