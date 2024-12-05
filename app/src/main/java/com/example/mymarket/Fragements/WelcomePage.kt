package com.example.mymarket.Fragements

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mymarket.R
import com.example.mymarket.activity.MainActivity

class WelcomePage : Fragment() {
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

        view.findViewById<TextView>(R.id.tologin).setOnClickListener {
            btnLogin.setBackgroundResource(R.drawable.toggle_selected)
            btnInscription.setBackgroundResource(R.drawable.toggle_unselected)
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, loginFragment())
                .commit()
        }
        view.findViewById<TextView>(R.id.tosignup).setOnClickListener {
            btnInscription.setBackgroundResource(R.drawable.toggle_selected)
            btnLogin.setBackgroundResource(R.drawable.toggle_unselected)
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, Inscriptionfragment())
                .commit()
        }
    }
}