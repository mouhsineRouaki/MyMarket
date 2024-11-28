package com.example.mymarket.DATA

import java.sql.Date

data class utilisateur(
    val id: Int,
    var nom: String,
    var prenom: String,
    val dateN: Date,
    val genere:String,
    val email: String,
    var password: String,
    var compteB : CompteBancaire? = null,
    var image: Int,
    val listP: List<Produit>,
)