package com.mortex.testapp.data.remote

import com.mortex.testapp.data.model.Category
import com.mortex.testapp.data.model.ResultModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface CatApi {
    @GET("Category")
     fun getCategories() : Deferred<ResultModel>
}