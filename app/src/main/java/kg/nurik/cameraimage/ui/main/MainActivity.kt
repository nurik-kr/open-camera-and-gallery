package kg.nurik.cameraimage.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kg.nurik.cameraimage.R
import kg.nurik.cameraimage.common.BaseUserPhotoActivity
import kg.nurik.cameraimage.common.pickPhotoFromGalleryWithPermissionCheck
import kg.nurik.cameraimage.common.shootPhotoWithPermissionCheck
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class MainActivity : BaseUserPhotoActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupClickListener()

        viewModel.authUser()

        val radius = resources.getDimension(R.dimen.imageRadius)
        val shape = image.shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, 40f)
            .setTopRightCorner(CornerFamily.ROUNDED, 40f)
            .setBottomLeftCorner(CornerFamily.ROUNDED, 40f)
            .setBottomRightCorner(CornerFamily.ROUNDED, 40f)
            .build()
        image.shapeAppearanceModel = shape
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.userData.observe(this, Observer {
            Picasso.get()
                .load(it.avatar)
                .into(image, object : Callback {
                    override fun onSuccess() {
                        Log.d("adssadasd", "adasdasd")
                    }

                    override fun onError(e: Exception?) {
                        Log.d("adssadasd", "adasdasd")
                    }
                })
        })
    }

    private fun setupClickListener() {
        button.setOnClickListener {
            shootPhotoWithPermissionCheck() //этот метод из base
        }
        buttonGallery.setOnClickListener {
            pickPhotoFromGalleryWithPermissionCheck()
        }
    }

    override fun showPhoto(file: File) {
//        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
//        image.setImageBitmap(bitmap)
        viewModel.updateUserWithPhoto(file)
    }

}