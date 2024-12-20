package com.example.mymarket.Service

import android.net.Uri
import com.example.mymarket.DATA.utilisateur
import com.example.mymarket.DATA.ville
import com.example.mymarket.DATA.villeType
import com.example.mymarket.interfaces.IDAO

object utilisateurService: IDAO<utilisateur> {
    private val produitCommande= mutableListOf<utilisateur>()
    val utulisiteurEnCours= mutableListOf<utilisateur>()
    override fun create(p: utilisateur): Boolean {
        utulisiteurEnCours.add(p)
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
    fun Clear(){
      produitCommande.clear()
    }

    override fun findAll(): MutableList<utilisateur> {
        return produitCommande.toMutableList()
    }
    fun findAllUtlisateur(): MutableList<utilisateur> {
        return utulisiteurEnCours.toMutableList()
    }
    fun ClearAndCreate(u:utilisateur){
        produitCommande.clear()
        produitCommande.add(u)
    }
    fun find(u:String):utilisateur?{
        return produitCommande.find { it.nom==u}
    }
    fun updateEmailPassword(p: utilisateur, email:String,password:String): Boolean {
        val index = produitCommande.indexOfFirst { it.nom ==p.nom }
        return if(index!=-1){
            p.email = email
            p.password = password
            produitCommande[index]=p
            true
        }else {
            false
        }
    }
    fun updateImage(p: utilisateur, image:Uri): Boolean {
        val index = produitCommande.indexOfFirst { it.id ==p.id }
        return if(index!=-1){
            p.image = image
            produitCommande[index]=p
            true
        }else {
            false
        }
    }
    fun getUser(): utilisateur {
        lateinit var v:utilisateur
        if (produitCommande.size == 1) {
            v=produitCommande[0]
        }
        return v
    }
    fun updateCompte(p: utilisateur,u:utilisateur): Boolean {
        val index = produitCommande.indexOfFirst { it.nom ==p.nom }
        return if(index!=-1){
            produitCommande[index]=u
            true
        }else {
            false
        }
    }

}