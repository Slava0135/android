package io.slava0135.lab5_4

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    val photoURL = "https://picsum.photos/seed/slava/900/1600"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        val circularProgressDrawable = CircularProgressDrawable(this).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        Glide
            .with(this)
            .load(photoURL)
            .placeholder(circularProgressDrawable)
            .into(imageView)
    }
}