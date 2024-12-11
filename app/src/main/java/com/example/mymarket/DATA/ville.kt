package com.example.mymarket.DATA

class ville(val id :Int,val ville:villeType,val timeLaivrison :Int) {
    companion object {
        private var cmp = 0

        fun incrementer(): Int {
            cmp += 1
            return cmp
        }
    }
    constructor(ville: villeType,timeLaivrison: Int):this(incrementer(),ville,timeLaivrison)
}