package com.example.bagstore.model.repository.comment

import com.example.bagstore.model.data.Comment

interface CommentRepository {

    suspend fun getAllComment(productId:String):List<Comment>
}