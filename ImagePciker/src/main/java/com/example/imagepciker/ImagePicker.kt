package com.example.imagepciker

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap

interface ImagePicker {
    fun actionOpenDocument(activity: Activity, onReceiveBitmapListener : (Bitmap) -> Unit)
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent, contentResolver: ContentResolver)

    companion object {
        fun newInstance(): ImagePicker {
            return ImagePickerImpl()
        }
    }
}