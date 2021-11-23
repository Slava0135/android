package ru.spbstu.icc.kspt.lab5_1.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {

    val tag = "CONTINUE_WATCH"

    val STATE_SECONDS = "secondsElapsed"
    var secondsElapsed: Int = 0

    lateinit var textSecondsElapsed: TextView

    lateinit var backgroundTask: Future<*>

    fun startBackgroundTask() = MyApplication.executorService.submit {
        Log.i(tag,"Background task started")
        try {
            while (!Thread.interrupted()) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.elapsed, secondsElapsed++)
                }
                Thread.sleep(1000)
                Log.i(tag, "Background task is running")
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
        backgroundTask.cancel(true)
    }

    override fun onStart() {
        super.onStart()
        backgroundTask = startBackgroundTask()
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
