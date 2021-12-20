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
import androidx.core.view.drawToBitmap
import com.google.android.material.snackbar.Snackbar
import java.lang.Error
import java.lang.Exception

import java.net.URL
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {

    val tag = "IMG"

    val photoURL = "https://picsum.photos/seed/slava/180/320"
    lateinit var downloadJob: Future<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        val mainThreadHandler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())

        downloadJob = downloadImage(mainThreadHandler) {
            progressBar.visibility = View.GONE
            imageView.setImageBitmap(it.getOrThrow())
        }
    }

    fun downloadImage(handler: Handler, callback: (Result<Bitmap>) -> Unit): Future<*> =
        (application as MyApplication).BACKGROUND.submit {
            try {
                Log.i(tag, "Started download")
                val url = URL(photoURL)
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                if (image != null) {
                    Log.i(tag, "Finished download")
                    handler.post { callback(Result.success(image)) }
                } else {
                    Log.i(tag, "Download failed")
                }
            } catch (e: Exception) {
                Log.i(tag, "Exceptions and Executors suck")
            }
        }

    override fun onDestroy() {
        downloadJob.cancel(true)
        super.onDestroy()
    }
}