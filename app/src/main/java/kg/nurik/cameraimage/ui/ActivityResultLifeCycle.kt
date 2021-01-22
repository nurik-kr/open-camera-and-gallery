package kg.nurik.cameraimage.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kg.nurik.cameraimage.ui.resultActivity.ResultActivity2

class ActivityResultLifeCycle(private val activity: AppCompatActivity) : LifecycleObserver {

    private val listener = activity as ResultActivityListener

    private val openActivity = activity.registerForActivityResult(ResultActivityContract()) {
        listener.textReturned(it)
    }

    init {
        activity.lifecycle.addObserver(this) // нша класс живет, столько сколько живет активити который его вызывает
    }

    fun openActivityWithText() {
        openActivity.launch(null)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        activity.lifecycle.removeObserver(this)//отписка чтоб утечки не было
        openActivity.unregister()
    }
}

class ResultActivityContract : ActivityResultContract<Unit, String>() {
    override fun createIntent(context: Context, input: Unit?): Intent {
        return Intent(context, ResultActivity2::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode == Activity.RESULT_OK) {
            return intent?.getStringExtra(TEXT_RETURNED)
        }
        return null
    }

    companion object {
        const val TEXT_RETURNED = "asadasdas"
    }
}

interface ResultActivityListener {
    fun textReturned(text: String)
}