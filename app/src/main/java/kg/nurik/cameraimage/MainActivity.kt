package kg.nurik.cameraimage

import android.graphics.BitmapFactory
import android.os.Bundle
import com.google.android.material.shape.CornerFamily
import kg.nurik.cameraimage.base.BaseUserPhotoActivity
import kg.nurik.cameraimage.base.pickPhotoFromGalleryWithPermissionCheck
import kg.nurik.cameraimage.base.shootPhotoWithPermissionCheck
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : BaseUserPhotoActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setupClickListener()
        val radius = resources.getDimension(R.dimen.imageRadius)
        val shape = image.shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, 40f)
            .setTopRightCorner(CornerFamily.ROUNDED, 40f)
            .setBottomLeftCorner(CornerFamily.ROUNDED, 40f)
            .setBottomRightCorner(CornerFamily.ROUNDED, 40f)
            .build()
        image.shapeAppearanceModel = shape

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
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        image.setImageBitmap(bitmap)
    }

}