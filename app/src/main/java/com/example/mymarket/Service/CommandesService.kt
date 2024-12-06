package com.example.mymarket.Service

import com.example.mymarket.DATA.Commandes
import com.example.mymarket.interfaces.IDAO

object CommandesService: IDAO<Commandes> {
    private val produitCommande= mutableListOf<Commandes>()
    override fun create(p:Commandes): Boolean {
        return produitCommande.add(p)
    }

    override fun update(p: Commandes,q:Int): Boolean {
        val index = produitCommande.indexOfFirst { it.Num ==p.Num }
        return if(index!=-1){
            p.prixTotal = q.toDouble()
            produitCommande[index]=p
            true
        }else {
            false
        }
    }

    override fun delete(o: Commandes): Boolean {
        return produitCommande.remove(o)
    }

    override fun findById(id: Int):Commandes? {
        return produitCommande.find {it.Num == id}
    }

    override fun findAll(): MutableList<Commandes> {
        return produitCommande.toMutableList()
    }
}