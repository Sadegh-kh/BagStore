package com.example.bagstore.model.repository

object TokenInMemory {
    var emailAddress: String? = null
        private set
    var token: String? = null
        private set

    fun refreshToken(emailAddress: String?, token: String?) {
        this.emailAddress = emailAddress
        this.token = token
    }
}