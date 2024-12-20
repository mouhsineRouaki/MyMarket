package com.example.mymarket.Fragements

import android.annotation.SuppressLint
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
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        val btnLogin = view.findViewById<Button>(R.id.buttonLogin)
        val retour = view.findViewById<ImageButton>(R.id.retour)
        btnLogin.setOnClickListener {
            val emailInput = email.text.toString().trim()
            val passwordInput = password.text.toString().trim()
            if (emailInput.isEmpty() || passwordInput.isEmpty()){
                Toast.makeText(requireContext(),"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val listComptes = utilisateurService.findAllUtlisateur().toMutableList()
            val utilisateurTrouve = listComptes.find { it.email == emailInput && it.password == passwordInput }
            if(utilisateurTrouve != null) {
                utilisateurService.ClearAndCreate(utilisateurTrouve)
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }else{
                Toast.makeText(requireContext(),"Mot de pass ou email incorrect",Toast.LENGTH_SHORT).show()
            }
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
    override fun onResume() {
        super.onResume()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun onPause() {
        super.onPause()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}