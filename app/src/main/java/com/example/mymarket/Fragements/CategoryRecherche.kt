package com.example.mymarket.Fragements

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.adapters.adapterCartProduit2
import com.example.mymarket.adapters.adapterPanier
import com.example.mymarket.adapters.adapterRechercheCategory

class CategoryRecherche: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.category_recherher, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewCategory: RecyclerView = view.findViewById(R.id.recycle_category)
        val recyclerViewProduit: RecyclerView = view.findViewById(R.id.recycle_produit_recherche)

        recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        recyclerViewProduit.layoutManager = GridLayoutManager(requireContext(), 2)

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
        val adapterCat = adapterRechercheCategory(listProduit)
        val adapterPro = adapterCartProduit2(listProduit)

        recyclerViewCategory.adapter = adapterCat
        recyclerViewProduit.adapter = adapterPro
    }
}