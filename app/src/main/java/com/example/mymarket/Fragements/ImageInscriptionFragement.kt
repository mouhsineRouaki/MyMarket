package com.example.mymarket.Fragements

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Outline
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
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
        imageView.apply {
            clipToOutline = true
            outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setOval(0, 0, view.width, view.height)
                }
            }
        }


            next.setOnClickListener {
                if (::imageSelected.isInitialized && imageSelected !=null) {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Creation de Compte!!")
                    builder.setMessage("      Votre Compte est Creer")
                    builder.setPositiveButton("OK") { dialog, which ->
                        val bundle = arguments
                        if (bundle != null) {
                            val nom = bundle.getString("nom")
                            if (nom == null) {
                                showToast("Le nom est null dans le Bundle")
                            } else {
                                showToast("Nom récupéré : $nom")
                                val user = utilisateurService.find(nom)
                                if (user == null) {
                                    showToast("Utilisateur introuvable")
                                }else{
                                    utilisateurService.updateImage(user,imageSelected)
                                    utilisateurService.ClearAndCreate(user)
                                }
                            }
                        } else {
                            showToast("Le Bundle est null")
                        }
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
    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}