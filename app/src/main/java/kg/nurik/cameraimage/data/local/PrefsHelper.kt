package kg.nurik.cameraimage.data.local

import android.content.Context
import android.content.SharedPreferences

object PrefsHelper {

    private const val PREFS_NAME = "kg.nurik.auth"
    private const val TOKEN = "TOKEN"
    private lateinit var prefs: SharedPreferences
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        prefs.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String {
        return prefs.getString(TOKEN, "") ?: "" //может быть null , и если  0 пускай вернет null
    }
}