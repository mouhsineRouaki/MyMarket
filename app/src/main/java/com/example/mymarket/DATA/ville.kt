package com.example.mymarket.DATA

class ville(val id :Int,val ville:villeType,val timeLaivrison :Long) {
    companion object {
        private var cmp = 0

        fun incrementer(): Int {
            cmp += 1
            return cmp
        }
    }
    constructor(ville: villeType,timeLaivrison: Long):this(incrementer(),ville,timeLaivrison)
}