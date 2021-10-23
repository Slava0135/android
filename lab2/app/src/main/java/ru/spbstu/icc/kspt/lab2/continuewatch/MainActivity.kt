package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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
    }

    override fun onStart() {
        super.onStart()
        isWorking = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SECONDS, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        secondsElapsed = savedInstanceState.getInt(STATE_SECONDS)
    }
}
