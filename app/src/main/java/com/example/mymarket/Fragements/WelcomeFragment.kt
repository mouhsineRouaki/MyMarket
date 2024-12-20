package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Produit
import com.example.mymarket.DATA.ville
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.CategoryService
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.Service.VilleService
import com.example.mymarket.Service.villeTypeService

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_comptes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = view.findViewById<TextView>(R.id.tologin)
        val btnInscription = view.findViewById<TextView>(R.id.tosignup)

        btnLogin.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_animations,
                    R.anim.aucun,
                    R.anim.pop_enter_animations,
                    R.anim.pop_sortie_animations
                )
                .replace(R.id.fragmentContainer, loginFragment())
                .commit()
        }

        btnInscription.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_animations,
                    R.anim.aucun,
                    R.anim.pop_enter_animations,
                    R.anim.pop_sortie_animations
                )
                .replace(R.id.fragmentContainer, Inscriptionfragment())
                .commit()
        }
    }
    override fun onResume() {
        super.onResume()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun onPause() {
        super.onPause()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}
