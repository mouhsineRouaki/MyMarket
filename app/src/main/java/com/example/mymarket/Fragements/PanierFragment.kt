package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.adapters.adapterPanier

class PanierFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.panier_activity, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclePanier)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,true)
        val listProduit= listOf(
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45)
        )
        val adapter = adapterPanier(listProduit) { produit ->
            Toast.makeText(requireContext(), "${produit.nomP} ajouté au panier !", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
    }
}