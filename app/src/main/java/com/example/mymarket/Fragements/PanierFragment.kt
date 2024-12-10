package com.example.mymarket.Fragements

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.CommandesService
import com.example.mymarket.Service.PanierService
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.adapters.adapterPanier

class PanierFragment: Fragment() {
    lateinit var totalPanier:TextView
    lateinit var adapter:adapterPanier
    lateinit var Produit :Produit
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.panier_activity, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclePanier)

        totalPanier = view.findViewById(R.id.Total)
        val btnCommande: Button = view.findViewById(R.id.btnCommander)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        val list =PanierService.findAll()
        adapter = adapterPanier(list,this,true) { produit ->
            Toast.makeText(requireContext(), "${produit.nomP} ajouté au panier !", Toast.LENGTH_SHORT).show()
        }
        val listtt = mutableListOf<Produit>()
        listtt.addAll(PanierService.findAll())
        btnCommande.setOnClickListener {
            val bottomSheetFragment = bottomLayoutFragement()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)

            val total = list.sumOf { it.prix * it.quantitePanier } - 0.29
            if (!PanierService.findAll().isEmpty()) {
                val copie= PanierService.findAll().toList()
                for(p in copie){
                    val c= if(p.quantite == p.quantitePanier){
                        p.quantite=0
                        p.quantite
                    }else{
                        p.quantite - p.quantitePanier
                    }
                    if(p.quantite ==0){
                        PanierService.delete(p)
                        adapter.notifyDataSetChanged()
                        updateTotal()
                    }
                    ProduitService.update(p,c)
                    PanierService.update(p,c)
                }
                if(!listtt.isEmpty()) {
                    CommandesService.create(Commandes(total, listtt))
                    Toast.makeText(requireContext(), "Commande Ajouter", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "list empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Toast.makeText(requireContext(), "Commande Ajouter", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Panier et vide ,commande refuser.", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = adapter
        updateTotal()

    }
    @SuppressLint("DefaultLocale")
    fun updateTotal(){
        var total=0.0
        val service = PanierService.findAll()
        for(e in service){
            if(e.Promo <=0){
                total+=e.prix * e.quantitePanier - 0.29
            }else {
                total += (e.prix * (1 - e.Promo / 100.0)) * e.quantitePanier - 0.29
            }
        }
        totalPanier.text = String.format("%.2f", total)
    }
}