package com.mortex.testapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mortex.testapp.R
import com.mortex.testapp.data.model.Category
import com.mortex.testapp.data.model.SubCategory
import kotlinx.android.synthetic.main.item_cat.view.*
import kotlinx.android.synthetic.main.sub_cat_item.view.*
import kotlin.properties.Delegates

class SubCatAdapter(private val clicled_ :Clicked) : RecyclerView.Adapter<SubCatAdapter.CatViewHolder>() {

    // Our data list is going to be notified when we assign a new list of data to it
    private var catsList: List<SubCategory> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.sub_cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int = catsList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        // Verify if position exists in list
        if (position != RecyclerView.NO_POSITION) {
            val category: SubCategory = catsList[position]
            holder.bind(category,clicled = clicled_)
        }
    }

    // Update recyclerView's data
    fun updateData(list: List<SubCategory>) {
        catsList = list
    }

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cat: SubCategory,clicled: Clicked) {
            itemView.sub_cat_name.text = cat.title
            itemView.setOnClickListener {
                clicled.selected(cat)
            }
        }
    }

}

interface Clicked {
    fun selected(subCategory: SubCategory)
}
