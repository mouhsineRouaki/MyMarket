package com.example.mymarket.Service

import android.net.Uri
import com.example.mymarket.DATA.utilisateur
import com.example.mymarket.interfaces.IDAO

object utilisateurService: IDAO<utilisateur> {
    private val produitCommande= mutableListOf<utilisateur>(
        utilisateur("mouhsine","rouaki","27/12/2004","Homme","1","1", Uri.parse("C:\\Users\\poste\\OneDrive\\Images\\html\\icon.webp"))
    )
    override fun create(p: utilisateur): Boolean {
        return produitCommande.add(p)
    }

    override fun update(p: utilisateur, q:Int): Boolean {
        val index = produitCommande.indexOfFirst { it.nom ==p.nom }
        return if(index!=-1){
            produitCommande[index]=p
            true
        }else {
            false
        }
    }

    override fun delete(o: utilisateur): Boolean {
        return produitCommande.remove(o)
    }

    override fun findById(id: Int): utilisateur? {
       TODO()
    }

    override fun findAll(): MutableList<utilisateur> {
        return produitCommande.toMutableList()
    }
    fun ClearAndCreate(u:utilisateur){
        produitCommande.clear()
        produitCommande.add(u)

    }
}