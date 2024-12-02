package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mymarket.R

class Inscription : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inscription_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nomUser = view.findViewById<EditText>(R.id.nomUser)
        val prenomUser = view.findViewById<EditText>(R.id.PrenomUser)
        val dateNaissance = view.findViewById<EditText>(R.id.dateNaissance)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)

        val radioGroup = view.findViewById<RadioGroup>(R.id.RadioGroup)
        val checkBoxConditions = view.findViewById<CheckBox>(R.id.condition)
        val buttonInscription = view.findViewById<Button>(R.id.buttonInscription)

        buttonInscription.setOnClickListener {
            val nom = nomUser.text.toString()
            val prenom = prenomUser.text.toString()
            val date = dateNaissance.text.toString()
            val emailValue = email.text.toString()
            val passwordValue = password.text.toString()

            val selectedSexeId = radioGroup.checkedRadioButtonId
            val sexe = if (selectedSexeId != -1) {
                view.findViewById<RadioButton>(selectedSexeId).text.toString()
            } else {
                "Non spécifié"
            }

            val conditionsAcceptees = checkBoxConditions.isChecked

            Toast.makeText(
                requireContext(),
                "Nom: $nom Prénom: $prenom Date: $date Email: $emailValue Password: $passwordValue Sexe: $sexe Conditions acceptées: $conditionsAcceptees",
                Toast.LENGTH_LONG
            ).show()

        }
    }
}
