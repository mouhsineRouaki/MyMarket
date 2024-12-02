package com.example.mymarket.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mymarket.R

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.splash_activity)
        Handler(Looper.getMainLooper()).postDelayed({
            val image = findViewById<ImageView>(R.id.logo)
            image.setImageResource(R.drawable.logo)
        }, 1000)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, GererComptes::class.java))
            finish()
        }, 4000)
    }
}