package kg.nurik.cameraimage.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kg.nurik.cameraimage.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity(), ResultActivityListener {

    private val resultListener: ActivityResultLifeCycle = ActivityResultLifeCycle(this)

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        btnClick.text = intent.data?.toString()

        btnClick.setOnClickListener {
//            getContent.launch("image/*")
            resultListener.openActivityWithText()
        }
    }

    override fun textReturned(text: String) {
        btnClick.text = text          //return parseResult
    }
}



