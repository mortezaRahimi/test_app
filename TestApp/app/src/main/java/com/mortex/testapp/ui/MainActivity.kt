package com.mortex.testapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mortex.testapp.R
import com.mortex.testapp.presentation.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity()  {

    private val viewModel:MainViewModel by viewModel()
    private lateinit var catAdapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catAdapter = CatAdapter()
        cat_rv.apply {
            // Displaying data in a Grid design
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = catAdapter
        }

        // Initiate the observers on viewModel fields and then starts the API request
        initViewModel()

    }

    private fun initViewModel() {
        // Observe catsList and update our adapter if we get new one from API
        viewModel.catsList.observe(this, Observer { newCatsList ->
            catAdapter.updateData(list = newCatsList.result!!)
        })
        // Observe showLoading value and display or hide our activity's progressBar
        viewModel.showLoading.observe(this, Observer { showLoading ->
            main_progress_bar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })
        // Observe showError value and display the error message as a Toast
        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        })
        // The observers are set, we can now ask API to load a data list
        viewModel.loadCats()
    }
}