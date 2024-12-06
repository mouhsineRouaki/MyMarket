package com.example.mymarket.Fragements

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mymarket.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class ProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profil_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFrancais = view.findViewById<TextView>(R.id.btnFrancais)
        val btnArabic = view.findViewById<TextView>(R.id.btnArabic)
        val btn_back = view.findViewById<ImageButton>(R.id.back_button)
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        btn_back.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragement())
                .commit()
            bottomNavigation?.selectedItemId = R.id.category
        }

        fun changeLanguage(locale: Locale) {
            val config = Configuration(resources.configuration)
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
            requireActivity().recreate()
        }



        btnFrancais.setOnClickListener {
            btnFrancais.setBackgroundResource(R.drawable.toggle_selected)
            btnArabic.setBackgroundResource(R.drawable.toggle_unselected)
            changeLanguage(Locale("fr"))
        }

        btnArabic.setOnClickListener {
            btnArabic.setBackgroundResource(R.drawable.toggle_selected)
            btnFrancais.setBackgroundResource(R.drawable.toggle_unselected)
            changeLanguage(Locale("ar"))
        }

        val currentLocale = resources.configuration.locale

        if (currentLocale.language == "fr") {
            btnFrancais.setBackgroundResource(R.drawable.toggle_selected)
            btnArabic.setBackgroundResource(R.drawable.toggle_unselected)
        } else {
            btnFrancais.setBackgroundResource(R.drawable.toggle_unselected)
            btnArabic.setBackgroundResource(R.drawable.toggle_selected)
        }
    }
}
