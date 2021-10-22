package io.slava0135.lab3_4

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about)
        findViewById<Button>(R.id.btn_to_act1).setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        findViewById<Button>(R.id.btn_to_act1).setBackgroundColor(Color.BLACK)
    }
}