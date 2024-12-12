package com.example.mymarket.Fragements

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.utilisateur
import com.example.mymarket.R
import com.example.mymarket.Service.utilisateurService

class ImageInscriptionFragement : Fragment() {
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageSelected: Uri
    lateinit var imageView:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_layout, container, false)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.ImageView)
        val imageButton = view.findViewById<TextView>(R.id.image)
        val next = view.findViewById<Button>(R.id.next)
        val nom = arguments?.getString("nom") ?: ""
        val prenom = arguments?.getString("prenom") ?: ""
        val date = arguments?.getString("date") ?: ""
        val genre = arguments?.getString("genre") ?: ""
        val ville = arguments?.getString("ville") ?: ""
        val email = arguments?.getString("email") ?: "c"
        val password = arguments?.getString("password") ?: "c"

            next.setOnClickListener {
                if (imageSelected != null) {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Creation de Compte!!")
                    builder.setMessage("Votre Compte est Creer\n$email $password")
                    builder.setPositiveButton("OK") { dialog, which ->
                        utilisateurService.create(
                            utilisateur(nom, prenom, date, genre, email, password, imageSelected)
                        )
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, loginFragment())
                            .commit()
                    }
                    val dialog = builder.create()
                    dialog.show()

                }else{
                    Toast.makeText(requireContext(), "please select image ", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

        imageButton.setOnClickListener {
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
                imageView.setImageURI(selectedImageUri)
                imageSelected =selectedImageUri
            }
        }
    }
}