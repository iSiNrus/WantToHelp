package ru.barsik.wanttohelp

import android.app.Application
import com.google.firebase.FirebaseApp

class App : Application() {
    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
    }
}