package io.slava0135.lab3_4

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.widget.Button

class Activity3 : OptionsBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3)
        findViewById<Button>(R.id.btn_to_act1).setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
        }
        findViewById<Button>(R.id.btn_to_act2).setOnClickListener {
            finish()
        }
    }
}