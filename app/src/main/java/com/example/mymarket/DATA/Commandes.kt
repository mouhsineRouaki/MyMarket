package com.example.mymarket.DATA

import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import com.example.mymarket.R
import com.example.mymarket.Service.NotificationService
import com.example.mymarket.Service.PanierService
import java.text.SimpleDateFormat
import java.util.Date

class Commandes(val Num:Int = incrementer(), val dateCmd : String, var status : String, var prixTotal : Double, var TotalCategory : Int, val ListProduits:MutableList<Produit>, val ville: ville, var stringTime :String="",var showNotificationEnAttente:Boolean=true,var showNotificationLivre:Boolean=true,var showNotificationWorker:Boolean=true) {
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
    fun startTimer(timeInMillis: Long,) {
        val currentTimer = object : CountDownTimer(timeInMillis+10_000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                stringTime = convertMillisToTime(millisUntilFinished)
            }

            override fun onFinish() {
                stringTime = "Livraison terminée!"
            }
        }
        currentTimer?.start()
    }
    fun convertMillisToTime(millis: Long): String {
        val totalSeconds = millis / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return String.format("%02dH :%02dM :%02dS", hours, minutes, seconds)
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

