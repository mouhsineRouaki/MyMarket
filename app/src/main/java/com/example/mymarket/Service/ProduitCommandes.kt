package com.example.mymarket.Service

import com.example.mymarket.DATA.Produit
import com.example.mymarket.interfaces.IDAO

class ProduitCommandes: IDAO<Produit> {
    private val produitCommande= mutableListOf<Produit>()
    override fun create(p:Produit): Boolean {
        return produitCommande.add(p)
    }

    override fun update(p: Produit,q:Int): Boolean {
        val index = produitCommande.indexOfFirst { it.nomP ==p.nomP }
        return if(index!=-1){
            p.quantite = q
            produitCommande[index]=p
            true
        }else {
            false
        }
    }

    override fun delete(o: Produit): Boolean {
        return produitCommande.remove(o)
    }

    override fun findById(id: Int):Produit? {
        return produitCommande.find {it.id == id}
    }

    override fun findAll(): MutableList<Produit> {
        return produitCommande.toMutableList()
    }
}