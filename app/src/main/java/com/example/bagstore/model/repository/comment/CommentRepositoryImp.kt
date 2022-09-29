package com.example.bagstore.model.repository.comment

import android.util.Log
import com.example.bagstore.model.data.Comment
import com.example.bagstore.model.net.ApiService
import com.example.bagstore.util.JsonProperty
import com.google.gson.JsonObject
import kotlin.math.log

class CommentRepositoryImp(private val apiService: ApiService) : CommentRepository {
    override suspend fun getAllComment(productId: String): List<Comment> {
        val productIdAsJson = JsonObject().apply {
            addProperty(JsonProperty.PRODUCT_ID, productId)
        }
        val response = apiService.getAllComment(productIdAsJson)
        if (response.success) {
            return response.comments
        }
        return listOf()
    }
}