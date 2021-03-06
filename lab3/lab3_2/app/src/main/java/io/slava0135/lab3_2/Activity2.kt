package io.slava0135.lab3_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class Activity2 : OptionsBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)
        findViewById<Button>(R.id.bnToFirst).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.bnToThird).setOnClickListener {
            startActivityForResult(Intent(this, Activity3::class.java), ACTIVITY_REQUEST)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ACTIVITY_REQUEST) {
            if (resultCode == RESULT_CLOSE) {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}