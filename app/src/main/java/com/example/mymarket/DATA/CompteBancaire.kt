package com.example.mymarket.DATA

import java.sql.Date

class CompteBancaire(
    val nom : String,
    val prenom : String,
    val rib : Int,
    val ccv : Int,
    val dateE : Date,
    val solde: Int = 3000

)