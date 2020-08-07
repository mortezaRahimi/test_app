package com.mortex.testapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mortex.testapp.R
import com.mortex.testapp.data.model.Category
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_cat.view.*
import kotlin.properties.Delegates

class CatAdapter(private val clicked_: Clicked) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    // Our data list is going to be notified when we assign a new list of data to it
    private var catsList: List<Category> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int = catsList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        // Verify if position exists in list
        val catAdapter = SubCatAdapter(clicked_)
        if (position != RecyclerView.NO_POSITION) {
            val category: Category = catsList[position]
            holder.bind(category,catAdapter)
        }
    }

    // Update recyclerView's data
    fun updateData(list: List<Category>) {
        catsList = list
    }

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cat: Category,subCatAdapter: SubCatAdapter) {
            itemView.cat_name.text = cat.title
            itemView.sub_cat_rv.apply {
                // Displaying data in a Grid design
                layoutManager = LinearLayoutManager(itemView.context)
                adapter = subCatAdapter
            }

            subCatAdapter.updateData(cat.subCategories)

            itemView.setOnClickListener {
                if (itemView.sub_cat_rv.visibility == View.VISIBLE)
                    itemView.sub_cat_rv.visibility = View.GONE
                else
                    itemView.sub_cat_rv.visibility = View.VISIBLE
            }
        }
    }
}