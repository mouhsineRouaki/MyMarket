package com.example.mymarket.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mymarket.Fragements.CategoryFragment
import com.example.mymarket.Fragements.CommandeFragment
import com.example.mymarket.Fragements.RechercheFragement
import com.example.mymarket.Fragements.HomeFragement
import com.example.mymarket.Fragements.PanierFragment
import com.example.mymarket.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = ContextCompat.getColor(this, R.color.textcolor)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        loadFragment(HomeFragement())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> loadFragment(HomeFragement())
                R.id.panier -> loadFragment(PanierFragment())
                R.id.search -> loadFragment(RechercheFragement())
                R.id.commandes -> loadFragment(CommandeFragment())
                R.id.category -> loadFragment(CategoryFragment())
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