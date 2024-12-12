package com.example.mymarket.Fragements

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.utilisateur
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.utilisateurService

class Inscriptionfragment : Fragment() {


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
        val retour = view.findViewById<ImageButton>(R.id.retour)
        val radioGroup = view.findViewById<RadioGroup>(R.id.RadioGroup)
        val checkBoxConditions = view.findViewById<CheckBox>(R.id.condition)
        val buttonInscription = view.findViewById<Button>(R.id.buttonInscription)
        val ville = view.findViewById<Spinner>(R.id.ville)
        val linear = view.findViewById<LinearLayout>(R.id.fragment_container2)


        val villesList = listOf(
            villeType.Safi, villeType.CasaBlanca,
            villeType.Tanger, villeType.Agadir
        )

        val adapter = object : ArrayAdapter<villeType>(
            requireContext(), android.R.layout.simple_spinner_item, villesList
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.gravity = Gravity.CENTER
                textView.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.inputTextColor)
                )
                return view
            }

            override fun getDropDownView(
                position: Int, convertView: View?, parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.gravity = Gravity.CENTER
                textView.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.inputTextColor)
                )
                return view
            }
        }
        ville.adapter = adapter

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

        buttonInscription.setOnClickListener {
            if (validateForm(
                    nomUser.text.toString(),
                    prenomUser.text.toString(),
                    dateNaissance.text.toString(),
                    radioGroup,
                    checkBoxConditions
                )
            ) {
                linear.visibility = View.GONE
                parentFragmentManager.beginTransaction().replace(R.id.frameInscription, emailAndPasswordFragement()).commit()
                val villeSelectionnee = ville.selectedItem.toString()
                utilisateurService.create(
                    utilisateur(nomUser.text.toString(), prenomUser.text.toString(), dateNaissance.text.toString(), radioGroup.checkedRadioButtonId.toString())
                )
                val bundle = Bundle()
                bundle.putString("nom", nomUser.text.toString())
                showToast("${nomUser.text}")
                val fragmentB = emailAndPasswordFragement()
                fragmentB.arguments = bundle
            }
        }
    }

    private fun validateForm(
        nom: String,
        prenom: String,
        date: String,
        radioGroup: RadioGroup,
        checkBoxConditions: CheckBox
    ): Boolean {
        if (nom.isEmpty() || prenom.isEmpty() || date.isEmpty() ) {
            showToast("Tous les champs doivent être remplis")
            return false
        }

        if (radioGroup.checkedRadioButtonId == -1) {
            showToast("Sélectionnez votre sexe")
            return false
        }

        if (!checkBoxConditions.isChecked) {
            showToast("Vous devez accepter les conditions")
            return false
        }
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
