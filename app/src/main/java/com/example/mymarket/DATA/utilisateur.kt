package com.example.mymarket.DATA

import android.net.Uri
import androidx.compose.ui.text.LinkAnnotation
import com.example.mymarket.R

data class utilisateur(
    val id: Int= incrementer(),
    var nom: String,
    var prenom: String,
    val dateN: String,
    val genere:String,
    var email: String,
    var password: String,
    var image: Uri,
){
    companion object {
        private var cmp = 0

        fun incrementer(): Int {
            cmp += 1
            return cmp
        }
    }
    constructor(
        nom: String,
        prenom: String,
        dateN: String,
        genere: String,
        email: String,
        password: String,
        image: Uri
    ):this(
        incrementer(),nom,prenom, dateN, genere, email, password, image
    )
    constructor(
        nom: String,
        prenom: String,
        dateN: String,
        genere: String,

    ):this(
        incrementer(),nom,prenom, dateN, genere,""," ",Uri.parse("android.resource://" + com.example.mymarket.R.drawable.ic_user + "/" + R.drawable.ic_user)
    )
}