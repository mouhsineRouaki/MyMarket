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

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageSelected: Uri

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
        val retour = view.findViewById<ImageButton>(R.id.retour)
        val radioGroup = view.findViewById<RadioGroup>(R.id.RadioGroup)
        val checkBoxConditions = view.findViewById<CheckBox>(R.id.condition)
        val buttonInscription = view.findViewById<Button>(R.id.buttonInscription)
        val ville = view.findViewById<Spinner>(R.id.ville)
        val image = view.findViewById<TextView>(R.id.image)

        image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

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
                    email.text.toString(),
                    password.text.toString(),
                    radioGroup,
                    checkBoxConditions
                )
            ) {
                val villeSelectionnee = ville.selectedItem.toString()
                showToast("Inscription réussie pour ${nomUser.text} ${prenomUser.text}")
            }
        }
    }

    private fun validateForm(
        nom: String,
        prenom: String,
        date: String,
        emailValue: String,
        passwordValue: String,
        radioGroup: RadioGroup,
        checkBoxConditions: CheckBox
    ): Boolean {
        if (nom.isEmpty() || prenom.isEmpty() || date.isEmpty() || emailValue.isEmpty() || passwordValue.isEmpty()) {
            showToast("Tous les champs doivent être remplis")
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            showToast("Email invalide")
            return false
        }

        if (passwordValue.length < 6) {
            showToast("Le mot de passe doit comporter au moins 6 caractères")
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

        utilisateurService.create(
            utilisateur(nom, prenom, date, radioGroup.checkedRadioButtonId.toString(), emailValue, passwordValue, imageSelected)
        )
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            if (selectedImageUri != null) {
                imageSelected = selectedImageUri
            }
        }
    }
}
