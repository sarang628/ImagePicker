package com.example.imagepicker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imagepciker.ImagePicker
import com.example.imagepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val imagePicker = ImagePicker.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnImagePick.setOnClickListener {
            imagePicker.actionOpenDocument(this){
                binding.ivBitmap.setImageBitmap(it)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let { imagePicker.onActivityResult(requestCode, resultCode, it, contentResolver) }

    }
}