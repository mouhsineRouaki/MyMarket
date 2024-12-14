package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.utilisateurService
import com.example.mymarket.Service.villeTypeService
import com.example.mymarket.adapters.adapterCategory

class InformationFragment : Fragment() {
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
        val villeSpinner =view. findViewById<Spinner>(R.id.villeSpinner)
        val genreSpinner =view. findViewById<Spinner>(R.id.genreSpinner)
        val infoSwitch =view. findViewById<Switch>(R.id.infoSwitch)
        val emailEditText =view. findViewById<EditText>(R.id.emailEditText)
        val passwordEditText =view. findViewById<EditText>(R.id.passwordEditText)
        val image =view.findViewById<ImageView>(R.id.avatarImageView)
        val user = utilisateurService.getUser()
        image.setImageURI(user.image)
        nomEditText.setText(user.nom)
        prenomEditText.setText(user.prenom)
        dateEditText.setText(user.dateN)
        val position = villeTypeService.findByPosition(user.ville.ville)
        villeSpinner.setSelection(position)
        val list = villeTypeService.findAll()
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,list)
        villeSpinner.adapter = adapter


        val submitButton =view. findViewById<Button>(R.id.Modifier)
        submitButton.setOnClickListener {
            val nom = nomEditText.text.toString()
            val prenom = prenomEditText.text.toString()
            val date = dateEditText.text.toString()
            val ville = villeSpinner.selectedItem.toString()
            val genre = genreSpinner.selectedItem.toString()
            val infoPersonnelle = infoSwitch.isChecked
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            Toast.makeText(
                requireContext(),
                "Nom: $nom\nPrénom: $prenom\nDate: $date\nVille: $ville\nGenre: $genre\n" +
                        "Infos Perso: $infoPersonnelle\nEmail: $email\nPassword: $password",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}