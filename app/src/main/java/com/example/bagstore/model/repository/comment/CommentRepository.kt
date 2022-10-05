package com.example.bagstore.model.repository.comment

import com.example.bagstore.model.data.Comment

interface CommentRepository {

    suspend fun getAllComment(productId:String):List<Comment>
    suspend fun addNewComment(productId: String,text:String,isSuccess:(String)->Unit)
}