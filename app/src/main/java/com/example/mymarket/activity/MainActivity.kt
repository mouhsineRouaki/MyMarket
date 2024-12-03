package com.example.mymarket.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mymarket.Fragements.CategoryRecherche
import com.example.mymarket.Fragements.HomeActivity
import com.example.mymarket.Fragements.PanierActivty
import com.example.mymarket.Fragements.login
import com.example.mymarket.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = ContextCompat.getColor(this, R.color.light_blue)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        loadFragment(HomeActivity())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> loadFragment(HomeActivity())
                R.id.panier -> loadFragment(PanierActivty())
                R.id.commandes -> loadFragment(HomeActivity())
                R.id.category -> loadFragment(CategoryRecherche())
            }
            true
        }
    }
    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}