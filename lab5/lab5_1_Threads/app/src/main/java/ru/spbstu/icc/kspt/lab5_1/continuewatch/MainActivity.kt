package ru.spbstu.icc.kspt.lab5_1.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val tag = "CONTINUE_WATCH"

    val STATE_SECONDS = "secondsElapsed"
    var secondsElapsed: Int = 0

    lateinit var textSecondsElapsed: TextView

    lateinit var backgroundThread: Thread

    fun createBackgroundThread() = Thread {
        Thread.currentThread().name = tag
        Log.i(tag, "Started new background thread")
        try {
            while (!Thread.interrupted()) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.elapsed, secondsElapsed++)
                }
                Log.i(tag, "Background thread is running")
                Thread.sleep(1000)
            }
        } catch (e: InterruptedException) {
            Log.i(tag, "Interrupted")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onStop() {
        super.onStop()
        backgroundThread.interrupt()
    }

    override fun onStart() {
        super.onStart()
        backgroundThread = createBackgroundThread()
        backgroundThread.start()
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
