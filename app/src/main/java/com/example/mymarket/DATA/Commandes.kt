package com.example.mymarket.DATA

import java.util.Date

class Commandes(val Num:Int = incrementer(), val dateCmd : Date, val status : String, val prixTotal : Double, val TotalCategory : Int){
    companion object {
        private var cmp = 0

        fun incrementer(): Int {
            cmp += 1
            return cmp
        }
    }
}