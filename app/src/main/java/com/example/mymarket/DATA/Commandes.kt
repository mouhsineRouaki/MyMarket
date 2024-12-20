package com.example.mymarket.DATA

import android.os.Handler
import android.os.Looper
import com.example.mymarket.R
import com.example.mymarket.Service.NotificationService
import com.example.mymarket.Service.PanierService
import java.text.SimpleDateFormat
import java.util.Date

class Commandes(val Num:Int = incrementer(), val dateCmd : String, var status : String, var prixTotal : Double, var TotalCategory : Int, val ListProduits:MutableList<Produit>, val ville: ville) {
    var totalprix = 0.0
    var totalcat = 0
    var list: Double = PanierService.findAll().sumOf { it.prix }
    val listCat: Int = totalcat + ListProduits.map { it.category }.toSet().size

    companion object {
        private var cmp = 0

        fun incrementer(): Int {
            cmp += 1
            return cmp
        }
    }

    constructor(prixTotal: Double, ListProduits: MutableList<Produit>, ville: ville) : this(
        incrementer(),
        SimpleDateFormat("dd/MM/yyyy").format(Date()),
        "En cours",
        prixTotal,
        ListProduits.map { it.category }.toSet().size,
        ListProduits,
        ville
    ){
        updateStatus()
    }
    fun updateStatus() {
        val handler = Handler(Looper.getMainLooper())

        when (status) {
            "En cours" -> {
                handler.postDelayed({
                    status = "En attente"
                    updateStatus()
                }, 10_000)
            }
            "En attente" -> {
                val tempsDeLivraison = ville.timeLaivrison
                handler.postDelayed({
                    status = "Livre"
                    NotificationService.create(Notification(R.drawable.livre,"la commande numero ${Num} est bien Livre"))
                }, tempsDeLivraison)
            }
        }
    }

}

