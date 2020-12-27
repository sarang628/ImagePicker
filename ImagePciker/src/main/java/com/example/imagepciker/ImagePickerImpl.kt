package com.example.imagepciker

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory

internal class ImagePickerImpl : ImagePicker {

    var onReceiveBitmapListener : ((Bitmap) -> Unit)? = null

    override fun actionOpenDocument(activity: Activity, onReceiveBitmapListener: (Bitmap) -> Unit) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = Companion.IMAGE_MIME_TYPE
        }
        activity.startActivityForResult(intent, Companion.IMAGE_PICKER_REQUEST_CODE)
        this.onReceiveBitmapListener = onReceiveBitmapListener
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent, contentResolver: ContentResolver) {
        if (requestCode == IMAGE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData.data?.let { uri ->
                onReceiveBitmapListener?.let {
                    it.invoke(BitmapFactory.decodeStream(contentResolver.openInputStream(uri)))
                }
                //showImage(uri)
            }
        }
    }

    companion object {
        const val IMAGE_MIME_TYPE = "image/*"
        const val IMAGE_PICKER_REQUEST_CODE = 1
    }
}