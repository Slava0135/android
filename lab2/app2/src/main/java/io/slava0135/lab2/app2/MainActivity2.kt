package io.slava0135.lab2.app2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    val STATE_SECONDS = "secondsElapsed"
    var secondsElapsed: Int = 0

    var isWorking = false

    lateinit var textSecondsElapsed: TextView

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            if (isWorking) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.elapsed, secondsElapsed++)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
    }


    override fun onStop() {
        super.onStop()
        isWorking = false
        getPreferences(MODE_PRIVATE)?.let {
            with (it.edit()) {
                putInt(STATE_SECONDS, secondsElapsed)
                apply()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        isWorking = true
        getPreferences(MODE_PRIVATE)?.let {
            secondsElapsed = it.getInt(STATE_SECONDS, secondsElapsed)
        }
    }
}
