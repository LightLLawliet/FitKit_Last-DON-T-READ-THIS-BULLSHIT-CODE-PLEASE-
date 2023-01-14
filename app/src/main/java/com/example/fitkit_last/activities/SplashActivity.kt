package com.example.fitkit_last.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.fitkit_last.MainActivity
import com.example.fitkit_last.R

class SplashActivity : AppCompatActivity() {
    private val splashScreenTime = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this@SplashActivity, MainActivity()::class.java)
                startActivity(intent)
                finish()
            }, splashScreenTime.toLong()
        )
    }
}