package io.slava0135.lab5_2

import android.app.Application
import java.util.concurrent.Executors

class MyApplication : Application() {
    val BACKGROUND = Executors.newSingleThreadExecutor()
}