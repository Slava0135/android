package io.slava0135.lab5_2

import android.app.Application
import java.util.concurrent.Executors

object MyApplication : Application() {
    val BACKGROUND = Executors.newFixedThreadPool(4)
}