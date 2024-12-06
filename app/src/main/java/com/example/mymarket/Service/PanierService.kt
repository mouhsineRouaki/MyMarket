package com.example.mymarket.Service

import com.example.mymarket.DATA.Produit
import com.example.mymarket.adapters.adapterPanier
import com.example.mymarket.interfaces.IDAO

object PanierService: IDAO<Produit> {
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
            return listPanier
        }
        fun deleteByPosition(P:Int){
            listPanier.removeAt(P)

        }
        val listTotal = mutableListOf<Double>()

        fun add(i:Double): Boolean {
            return listTotal.add(i)
        }
        fun remove(i:Double): Boolean {
            return listTotal.remove(i)
        }
        fun getTotal():Double{
            return listTotal.sumOf { it }
        }

}