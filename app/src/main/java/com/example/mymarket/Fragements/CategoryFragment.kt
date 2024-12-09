package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

class CategoryFragment : Fragment() {

    lateinit var categoryList: MutableList<Category>
    lateinit var produitList: MutableList<Produit>
    lateinit var produitsFiltrer: MutableList<Produit>
    lateinit var categoryAdapter: adapterCategory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewCategory = view.findViewById<RecyclerView>(R.id.recycle_category)
        val recyclerViewProduit = view.findViewById<RecyclerView>(R.id.recycle_category_produit)
        val editTextSearch = view.findViewById<EditText>(R.id.searchEditText)

        categoryList = CategoryService.findAll().toMutableList()
        produitList = ProduitService.findAll().toMutableList()
        produitsFiltrer = produitList.toMutableList()

        categoryAdapter = adapterCategory(categoryList) { category ->
            category.Select = !category.Select
            filterProduitsByCategory()
            updateProduitRecyclerView(recyclerViewProduit)
        }

        recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory.adapter = categoryAdapter

        recyclerViewProduit.layoutManager = GridLayoutManager(requireContext(), 2)
        updateProduitRecyclerView(recyclerViewProduit)

        editTextSearch.doAfterTextChanged { text ->
            if (text.isNullOrEmpty()) {
                filterProduitsByCategory()
            } else {
                filterProduitsBySearch(text.toString().uppercase())
            }
            updateProduitRecyclerView(recyclerViewProduit)
        }
    }

    private fun filterProduitsByCategory() {
        val selectedCategories = categoryList.filter { it.Select }.map { it.nom }
        if (selectedCategories.isEmpty()) {
            produitsFiltrer=produitList.toSet().toMutableList()
        } else {
            produitsFiltrer=produitList.filter { it.category in selectedCategories }.toSet().toMutableList()
        }
    }

    private fun filterProduitsBySearch(query: String) {
        produitsFiltrer = produitList.filter { it.nomP.uppercase().startsWith(query) }.toSet().toMutableList()
    }

    private fun updateProduitRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapterCartProduit2(produitsFiltrer, parentFragmentManager)
    }
}
