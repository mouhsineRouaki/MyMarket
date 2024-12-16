package com.example.mymarket.Service

import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Produit
import com.example.mymarket.interfaces.IDAO

object CategoryService: IDAO<Category> {
    private val listCategory= mutableListOf<Category>()
    val listPanierAr= mutableListOf<Category>()

    override fun create(p: Category): Boolean {
        return listCategory.add(p)
    }
    fun createArabe(p: Category): Boolean {
        return listPanierAr.add(p)
    }
    fun findAllArabe(): MutableList<Category> {
        return listPanierAr
    }

    override fun update(p: Category, q:Int): Boolean {
        val index = listCategory.indexOfFirst { it.nom ==p.nom }
        return if(index!=-1){
            listCategory[index]=p
            true
        }else {
            false
        }
    }

    override fun delete(o: Category): Boolean {
        return listCategory.remove(o)
    }

    override fun findById(id: Int): Category?{
        TODO()
    }

    override fun findAll(): MutableList<Category> {
        return listCategory.toMutableList()
    }
    fun ChangeSelect(p: Category): Boolean {
        val index = listCategory.indexOfFirst { it.nom ==p.nom }
        return if(index!=-1){
            p.Select = !p.Select
            listCategory[index]=p
            true
        }else {
            false
        }
    }

}