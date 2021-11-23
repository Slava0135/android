package ru.spbstu.icc.kspt.lab5_1.continuewatch

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val tag = "CONTINUE_WATCH"

    val STATE_SECONDS = "millisecondsElapsed"
    var milliSecondsElapsed = 0L
    var timeStart = 0L
    var wasTimeUpdated = false

    lateinit var textSecondsElapsed: TextView

    suspend fun runBackgroundTask() {
        while (true) {
            textSecondsElapsed.post {
                textSecondsElapsed.text = getString(R.string.elapsed, getTimeTotalMillis() / 1000)
            }
            Log.i(tag, "Background task is running")
            delay(1000)
        }
    }

    fun getTimeTotalMillis() = milliSecondsElapsed + getTimeDiffMillis()
    fun getTimeDiffMillis() = SystemClock.uptimeMillis() - timeStart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.i(tag, "Starting new task")
                runBackgroundTask()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        timeStart = SystemClock.uptimeMillis()
        wasTimeUpdated = false
    }

    override fun onStop() {
        super.onStop()
        milliSecondsElapsed = getTimeTotalMillis()
        wasTimeUpdated = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (!wasTimeUpdated) milliSecondsElapsed = getTimeTotalMillis()
        outState.putLong(STATE_SECONDS, milliSecondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        milliSecondsElapsed = savedInstanceState.getLong(STATE_SECONDS)
    }
}
