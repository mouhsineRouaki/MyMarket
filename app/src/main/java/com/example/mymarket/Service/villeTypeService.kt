package com.example.mymarket.Service

import com.example.mymarket.DATA.villeType
import com.example.mymarket.interfaces.IDAO

object villeTypeService : IDAO<villeType> {
    private val produitCommande= mutableListOf<villeType>()
    override fun create(p: villeType): Boolean {
        return produitCommande.add(p)
    }

    override fun update(p: villeType, q:Int): Boolean {
        TODO()
    }

    override fun delete(o: villeType): Boolean {
        return produitCommande.remove(o)
    }

    override fun findById(id: Int): villeType? {
        TODO()
    }

    override fun findAll(): MutableList<villeType> {
        return produitCommande.toMutableList()
    }
    fun findByNom(villeType: villeType): villeType? {
        return  produitCommande.find { it == villeType}
    }
    fun findByPosition(villeType: villeType): Int {
        return produitCommande.indexOfFirst{ it == villeType}
    }
}