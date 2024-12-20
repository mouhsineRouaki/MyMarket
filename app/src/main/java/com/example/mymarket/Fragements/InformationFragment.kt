package com.example.mymarket.Fragements

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Produit
import com.example.mymarket.DATA.utilisateur
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.VilleService
import com.example.mymarket.Service.utilisateurService
import com.example.mymarket.Service.villeTypeService
import com.example.mymarket.adapters.adapterCategory

class InformationFragment : Fragment() {
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageSelected: Uri
    lateinit var imageView:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profil_layoutt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nomEditText =view.findViewById<EditText>(R.id.nomEditText)
        val prenomEditText =view. findViewById<EditText>(R.id.prenomEditText)
        val dateEditText =view. findViewById<EditText>(R.id.dateEditText)
        val infoPer = view.findViewById<LinearLayout>(R.id.infoPersonelle)
        val villeSpinner =view. findViewById<Spinner>(R.id.villeSpinner)
        val genreSpinner =view. findViewById<Spinner>(R.id.genreSpinner)
        val infoSwitch =view. findViewById<Switch>(R.id.infoSwitch)
        val emailEditText =view. findViewById<EditText>(R.id.emailEditText)
        val TextButton =view. findViewById<TextView>(R.id.textButton)
        val passwordEditText =view. findViewById<EditText>(R.id.passwordEditText)
        val backimageView =view.findViewById<ImageView>(R.id.backImageView)
        imageView =view.findViewById<ImageView>(R.id.avatarImageView)
        val user = utilisateurService.getUser()
        imageView.setImageURI(user.image)
        imageSelected = user.image
        nomEditText.setText(user.nom)
        prenomEditText.setText(user.prenom)
        dateEditText.setText(user.dateN)
        emailEditText.setText(user.email)
        passwordEditText.setText(user.password)
        backimageView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfilFragment())
                .commit()
        }

        val position = villeTypeService.findByPosition(user.ville.ville)
        val list = villeTypeService.findAll()
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,list)
        villeSpinner.adapter = adapter
        villeSpinner.setSelection(position)

        val listGenre= listOf("homme","femme")
        val adapterGenre = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,listGenre)
        genreSpinner.adapter = adapterGenre
        val positionGenre = if(user.genere == "homme"){0} else {1}
        villeSpinner.setSelection(positionGenre)
        infoPer.visibility = View.GONE
        infoSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                infoPer.visibility = View.VISIBLE
            } else {
                infoPer.visibility = View.GONE
            }
        }


        val submitButton =view. findViewById<Button>(R.id.Modifier)
        submitButton.setOnClickListener {
            val nom = nomEditText.text.toString().trim()
            val prenom = prenomEditText.text.toString().trim()
            val date = dateEditText.text.toString().trim()
            val ville = villeSpinner.selectedItem?.toString()?.trim() ?: ""
            val genre = genreSpinner.selectedItem?.toString()?.trim() ?: ""
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (nom.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez entrer votre nom.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (prenom.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez entrer votre prénom.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (date.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez entrer une date valide.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (ville.isEmpty() || ville == "Sélectionnez une ville") {
                Toast.makeText(requireContext(), "Veuillez sélectionner une ville.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (genre.isEmpty() || genre == "Sélectionnez un genre") {
                Toast.makeText(requireContext(), "Veuillez sélectionner un genre.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(infoSwitch.isChecked) {

                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches()
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Veuillez entrer une adresse email valide.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                if (password.isEmpty() || password.length < 6) {
                    Toast.makeText(
                        requireContext(),
                        "Le mot de passe doit comporter au moins 6 caractères.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }
            val villee=VilleService.findByNom(villeSpinner.selectedItem as villeType)
            if (villee != null) {
                val u = utilisateur(nom, prenom, date, genre, email, password, imageSelected, villee)
                utilisateurService.updateCompte(user,u)
            }else{
                Toast.makeText(requireContext(), "Ville null", Toast.LENGTH_SHORT).show()
            }
        }
        TextButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            if (selectedImageUri != null) {
                imageSelected =selectedImageUri
                imageView.setImageURI(selectedImageUri)
            }
        }
    }
}