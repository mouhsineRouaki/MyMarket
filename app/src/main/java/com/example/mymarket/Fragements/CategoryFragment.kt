package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.CategoryService
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.adapters.adapterCartProduit2
import com.example.mymarket.adapters.adapterCategory
import com.example.mymarket.adapters.adapterCommandes
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoryFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView=view.findViewById<RecyclerView>(R.id.recycle_category)
        val recyclerView1=view.findViewById<RecyclerView>(R.id.recycle_category_produit)
        val editText=view.findViewById<EditText>(R.id.searchEditText)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        val adapter = adapterCategory(CategoryService.findAll())
        recyclerView.adapter = adapter

        recyclerView1.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter1 = adapterCartProduit2(ProduitService.findAll(),parentFragmentManager)
        recyclerView1.adapter = adapter1

    }

}