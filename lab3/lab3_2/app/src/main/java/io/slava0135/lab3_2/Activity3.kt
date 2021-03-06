package io.slava0135.lab3_2

import android.os.Bundle
import android.widget.Button

class Activity3 : OptionsBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3)
        findViewById<Button>(R.id.bnToFirst).setOnClickListener {
            setResult(RESULT_CLOSE)
            finish()
        }
        findViewById<Button>(R.id.bnToSecond).setOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}