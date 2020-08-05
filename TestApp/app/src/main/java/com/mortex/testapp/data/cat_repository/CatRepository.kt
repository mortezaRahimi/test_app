package com.mortex.testapp.data.cat_repository

import com.mortex.testapp.data.UseCaseResult
import com.mortex.testapp.data.model.Category
import com.mortex.testapp.data.model.ResultModel
import com.mortex.testapp.data.remote.CatApi


interface CatRepository {
    // Suspend is used to await the result from Deferred
    suspend fun getCatList(): UseCaseResult<ResultModel>
}

class CatRepositoryImpl(private val catApi: CatApi) : CatRepository {
    override suspend fun getCatList(): UseCaseResult<ResultModel> {
        /*
         We try to return a list of cats from the API
         Await the result from web service and then return it, catching any error from API
         */
        return try {
            val result = catApi.getCategories().await()
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}