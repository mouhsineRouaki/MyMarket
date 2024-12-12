package com.example.mymarket.DATA

import com.example.mymarket.R

class Category(val image :Int,val nom:String,var Select: Boolean = false) {
    constructor(image: Int,nom: String):this(image,nom,false)
    constructor(nom: String,Select: Boolean):this(R.drawable.logo,nom,Select)
}