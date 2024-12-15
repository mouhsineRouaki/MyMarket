package com.example.mymarket.DATA

import android.net.Uri
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
    val ville:ville
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
        image: Uri,
        ville: ville
    ):this(
        incrementer(),nom,prenom, dateN, genere, email, password, image,ville
    )
    constructor(
        nom: String,
        prenom: String,
        dateN: String,
        genere: String,
        ville: ville

    ):this(
        incrementer(),nom,prenom, dateN, genere,"houtm27@gmail.com","111111",Uri.parse(""),ville
    )
}