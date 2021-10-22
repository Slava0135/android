package io.slava0135.lab3_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Activity2 : OptionsBase() {

    val COUNT = "count"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        findViewById<Button>(R.id.btn_to_act1).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.btn_to_act3).setOnClickListener {
            startActivity(Intent(this, Activity3::class.java))
        }
        findViewById<Button>(R.id.btn_update).setOnClickListener {
            val intent = Intent(Intent(this, Activity2::class.java))
            intent.putExtra(COUNT, findViewById<TextView>(R.id.count).text)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.extras?.getString(COUNT)?.let {
            findViewById<TextView>(R.id.count).text = it + "A"
        }
    }

}