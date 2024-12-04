package com.example.mymarket.DATA

import com.example.mymarket.R

class Category(val image :Int,val nom:String) {
    constructor(nom: String):this(R.drawable.logo,nom)
}