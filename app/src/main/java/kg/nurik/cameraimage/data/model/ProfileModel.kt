package kg.nurik.cameraimage.data.model

import  android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileModel(
    val id: Int,
    val email: String?,
    @SerializedName("role_code")
    val roleCode: String?,
    var phone: String?,
    @SerializedName("status_code")
    val statusCode: String?,
    @SerializedName("first_name")
    var firstName: String?,
    @SerializedName("last_name")
    var lastName: String?,
    var birthdate: String?,
    var gender: String?,
    @SerializedName("about_me")
    var aboutMe: String,
    @SerializedName("instagram_link")
    var instagramLink: String?,
    @SerializedName("vk_link")
    var vkLink: String?,
    var avatar: String?

) : Parcelable