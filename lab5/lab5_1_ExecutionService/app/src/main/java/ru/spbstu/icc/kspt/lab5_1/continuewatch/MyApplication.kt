package ru.spbstu.icc.kspt.lab5_1.continuewatch

import android.app.Application
import java.util.concurrent.Executors

object MyApplication : Application() {
    val executorService = Executors.newFixedThreadPool(4)
}