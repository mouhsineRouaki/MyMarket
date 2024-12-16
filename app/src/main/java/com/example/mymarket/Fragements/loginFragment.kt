package com.example.mymarket.Fragements

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.utilisateur
import com.example.mymarket.DATA.ville
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.utilisateurService
import com.example.mymarket.activity.MainActivity

class loginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_activity, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        val btnLogin = view.findViewById<Button>(R.id.buttonLogin)
        val retour = view.findViewById<ImageButton>(R.id.retour)
        btnLogin.setOnClickListener {
            val emailInput = email.text.toString().trim()
            val passwordInput = password.text.toString().trim()

            val listComptes = utilisateurService.findAll().toMutableList()
            val utilisateurTrouve = listComptes.find { it.email == emailInput && it.password == passwordInput }
            val u =utilisateur("mohsin","rouaki","21/12/2004","homme", ville(villeType.Agadir,10_000))

                utilisateurService.ClearAndCreate(u)
                startActivity(Intent(requireContext(), MainActivity::class.java))
        }
        retour.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.sortie_animations,
                    R.anim.sortie_animations,
                    R.anim.pop_enter_animations,
                    R.anim.pop_sortie_animations
                )
                .replace(R.id.fragmentContainer, WelcomeFragment())
                .commit()

        }
    }
}