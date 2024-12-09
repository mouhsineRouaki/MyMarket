package com.example.mymarket.DATA

import com.example.mymarket.R

class Produit(
    val id : Int,
    val nomP : String,
    val description : String,
    val prix : Double,
    var category: String,
    val image : Int,
    var quantite : Int,
    var Promo:Int = 0,
    var quantitePanier:Int=1
){
    companion object {
        private var cmp = 0

        fun incrementer(): Int {
            cmp += 1
            return cmp
        }
    }
    constructor(nomP: String,description: String,prix: Double,category: String,quantite: Int):this(
        incrementer(),nomP,description,prix,category, R.drawable.logo,quantite
    )
    constructor(image:Int,nomP: String,description: String,prix: Double,category: String,quantite: Int):this(
        incrementer(),nomP,description,prix,category, image,quantite
    )
    constructor(nomP: String,description: String,prix: Double,category: String,quantite: Int,Promo: Int):this(
        incrementer(),nomP,description,prix,category, R.drawable.logo,quantite,Promo
    )
    constructor(nomP: String,description: String,prix: Double,category: String,quantite: Int,Promo: Int,quantitePanier: Int):this(
        incrementer(),nomP,description,prix,category, R.drawable.logo,quantite,Promo,quantitePanier
    )
}