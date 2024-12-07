package com.example.mymarket.DATA

import com.example.mymarket.R

class Category(val image :Int,val nom:String,var Select: Boolean = false) {
    constructor(nom: String):this(R.drawable.logo,nom)
    constructor(nom: String,Select: Boolean):this(R.drawable.logo,nom,Select)
}