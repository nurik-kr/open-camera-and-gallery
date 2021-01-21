package kg.nurik.cameraimage

import android.app.Application
import kg.nurik.cameraimage.data.local.PrefsHelper
import kg.nurik.cameraimage.di.appModules
import org.koin.android.ext.android.startKoin

class CameraImageApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PrefsHelper.init(this)
        startKoin(this, appModules)
    }
}