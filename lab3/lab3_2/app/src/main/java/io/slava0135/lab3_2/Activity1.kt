package io.slava0135.lab3_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class Activity1 : OptionsBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)
        findViewById<Button>(R.id.bnToSecond).setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
        }
    }
}