package io.slava0135.lab5_2

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.HandlerCompat
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

import java.net.URL
import java.util.concurrent.CompletableFuture

class MainActivity : AppCompatActivity() {

    val photoURL = "https://picsum.photos/seed/slava/180/320"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        val mainThreadHandler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())

        downloadImage(mainThreadHandler) {
            progressBar.visibility = View.GONE
            imageView.setImageBitmap(it.getOrThrow())
        }
    }

    fun downloadImage(handler: Handler, callback: (Result<Bitmap>) -> Unit) {
        MyApplication.BACKGROUND.execute {
            try {
                val url = URL(photoURL)
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                handler.post { callback(Result.success(image)) }
            } catch (e: Exception) {
                handler.post { callback(Result.failure(e)) }
            }
        }
    }
}