package io.slava0135.lab3_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class Activity2 : OptionsBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)
        findViewById<Button>(R.id.btn_to_act1).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.btn_to_act3).setOnClickListener {
            startActivity(Intent(this, Activity3::class.java))
        }
    }

}