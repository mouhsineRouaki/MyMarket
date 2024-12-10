package com.example.mymarket.Service

import com.example.mymarket.DATA.Produit
import com.example.mymarket.interfaces.IDAO

object ProduitService : IDAO<Produit> {
    private val listPanier= mutableListOf<Produit>()
    override fun create(p: Produit): Boolean {
        return listPanier.add(p)
    }

    override fun update(p: Produit, q:Int): Boolean {
        val index = listPanier.indexOfFirst { it.nomP ==p.nomP }
        return if(index!=-1){
            p.quantite = q
            listPanier[index]=p
            true
        }else {
            false
        }
    }

    override fun delete(o: Produit): Boolean {
        return listPanier.remove(o)
    }

    override fun findById(id: Int): Produit? {
        return listPanier.find {it.id == id}
    }

    override fun findAll(): MutableList<Produit> {
        return listPanier.toMutableList()
    }
    fun Clear(){
        listPanier.clear()
    }
    fun updateQantite(p: Produit, q:Int){
        val index = listPanier.indexOfFirst { it.nomP ==p.nomP }
        if(index!=-1){
            p.quantite = q
            listPanier[index]=p
        }
    }
}