package com.mortex.testapp.data.model

data class Category(
    val id: String,
    val subCategories: List<SubCategory>,
    val title: String
)