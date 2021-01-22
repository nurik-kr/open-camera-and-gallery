package kg.nurik.cameraimage.ui.resultActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kg.nurik.cameraimage.R
import kg.nurik.cameraimage.ui.ResultActivityContract.Companion.TEXT_RETURNED

class ResultActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result2)

        Handler(Looper.getMainLooper()).postDelayed({
            setResult(
                Activity.RESULT_OK,
                Intent().apply { putExtra(TEXT_RETURNED, "123456789") })
            finish()
        }, 3000) //тут получаем и отправляем
    }
}