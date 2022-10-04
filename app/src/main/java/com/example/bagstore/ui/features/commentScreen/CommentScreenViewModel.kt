package com.example.bagstore.ui.features.commentScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.data.Comment
import com.example.bagstore.model.repository.comment.CommentRepository
import com.example.bagstore.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class CommentScreenViewModel(productId:String,private val commentRepository: CommentRepository):ViewModel() {
    val commentListState= mutableStateOf<List<Comment>>(listOf())
    init {
        getAllComment(productId)
    }

    private fun getAllComment(productId: String){
        viewModelScope.launch(context = coroutineExceptionHandler) {
            commentListState.value=commentRepository.getAllComment(productId)
        }
    }

}