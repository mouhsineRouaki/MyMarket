package com.example.mymarket.DATA

import com.example.mymarket.Service.PanierService
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

class Commandes(val Num:Int = incrementer(), val dateCmd : String, val status : String, var prixTotal : Double, var TotalCategory : Int,val ListProduits:MutableList<Produit>){
    var totalprix = 0.0
    var totalcat= 0
    var list:Double = PanierService.findAll().sumOf{ it.prix }
    val listCat:Int = totalcat+ListProduits.map{it.category}.toSet().size
    companion object {
        private var cmp = 0

        fun incrementer(): Int {
            cmp += 1
            return cmp
        }
    }
    constructor(prixTotal: Double,ListProduits: MutableList<Produit>):this(incrementer(), SimpleDateFormat("dd/MM/yyyy").format(Date()),"EN Cours",prixTotal,ListProduits.map{it.category}.toSet().size,ListProduits)
}
