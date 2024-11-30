package com.example.mymarket

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.splash_activity)
        Handler(Looper.getMainLooper()).postDelayed({
<<<<<<< HEAD
            val image = findViewById<ImageView>(R.id.logo)
            image.setImageResource(R.drawable.logo)
        }, 1000)
        Handler(Looper.getMainLooper()).postDelayed({
=======
>>>>>>> 8fb32b5a486a16a9f4d272a477d3d4793f6c2735
            startActivity(Intent(this, GererComptes::class.java))
            finish()
        }, 4000)
    }
}