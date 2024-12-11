package com.example.mymarket.Service

import com.example.mymarket.DATA.ville
import com.example.mymarket.interfaces.IDAO

object VilleService : IDAO<ville> {
    private val produitCommande= mutableListOf<ville>()
    override fun create(p: ville): Boolean {
        return produitCommande.add(p)
    }

    override fun update(p: ville, q:Int): Boolean {
        val index = produitCommande.indexOfFirst { it.ville ==p.ville }
        return if(index!=-1){
            produitCommande[index]=p
            true
        }else {
            false
        }
    }

    override fun delete(o: ville): Boolean {
        return produitCommande.remove(o)
    }

    override fun findById(id: Int): ville? {
        TODO()
    }

    override fun findAll(): MutableList<ville> {
        return produitCommande.toMutableList()
    }
}