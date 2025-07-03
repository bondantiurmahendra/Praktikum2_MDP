package com.example.praktikum2.model.response

data class RegisterResponse(
    val message: String,
    val user: RegisterUser
)

data class RegisterUser(
    val id: String,
    val email: String,
    val username: String,
)