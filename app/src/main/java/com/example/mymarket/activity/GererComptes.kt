package com.example.mymarket.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.mymarket.R

import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.mymarket.Fragements.WelcomeFragment

class GererComptes: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_comptes)

        val btnLogin = findViewById<TextView>(R.id.tologin)
        val btnInscription = findViewById<TextView>(R.id.tosignup)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, WelcomeFragment()).commit()


    }
}
