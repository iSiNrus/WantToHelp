package ru.barsik.wanttohelp.ui

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.barsik.wanttohelp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            },
            2000
        )
    }
}