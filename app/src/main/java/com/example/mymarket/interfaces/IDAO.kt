package com.example.mymarket.interfaces

import com.example.mymarket.DATA.Produit

interface IDAO<T> {
    fun create(p:T):Boolean
    fun update(p:T,q :Int):Boolean
    fun delete(p:T):Boolean
    fun findById(id:Int): Produit?
    fun findAll():List<T>
}