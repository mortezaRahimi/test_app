package com.mortex.testapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mortex.testapp.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra("ANSWER")
        detail_title.text = title

        in_person_rb.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked)
                detail_title.visibility = View.VISIBLE
            else
                detail_title.visibility = View.GONE
        }

    }
}