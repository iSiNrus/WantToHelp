package ru.barsik.wanttohelp

import android.app.Application
import com.google.firebase.FirebaseApp
import ru.barsik.wanttohelp.di.*

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .domainModule(DomainModule())
            .dataModule(DataModule(context = this))
            .build()
    }
}