package com.example.mymarket.Fragements

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.mymarket.R
import com.example.mymarket.Service.utilisateurService

class emailAndPasswordFragement : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.email_password_layout, container, false)
    }

    @SuppressLint("CommitTransaction")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        val next = view.findViewById<Button>(R.id.next)
        val linear = view.findViewById<LinearLayout>(R.id.fragment_container2)


        next.setOnClickListener {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                showToast("Email invalide")
                return@setOnClickListener
            }

            if (password.text.toString().length < 6) {
                showToast("Le mot de passe doit comporter au moins 6 caracteres")
                return@setOnClickListener
            }
            val bundle = arguments
            if (bundle != null) {
                val nom = bundle.getString("nom")
                val fragment = ImageInscriptionFragement()
                val bundlee = Bundle()
                bundlee.putString("nom", nom)
                fragment.arguments = bundlee

                if (nom == null) {
                    showToast("Le nom est null dans le Bundle")
                } else {
                    showToast("Nom récupéré : $nom")
                    val user = utilisateurService.find(nom)
                    if (user != null) {
                        utilisateurService.updateEmailPassword(user,email.text.toString(),password.text.toString())
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.frameInscription, fragment)
                            .commit()
                    }else{
                        showToast("Utilisateur introuvable")
                    }
                }
            } else {
                showToast("Le Bundle est null")
            }


        }
    }
    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}
