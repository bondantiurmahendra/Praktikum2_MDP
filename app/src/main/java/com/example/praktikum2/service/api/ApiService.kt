package com.example.praktikum2.service

import com.example.praktikum2.model.request.LoginRequest
import com.example.praktikum2.model.request.RegisterRequest
import com.example.praktikum2.model.response.LoginResponse
import com.example.praktikum2.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("/api/register")
    suspend fun register(@Body request: RegisterRequest):
            Response<RegisterResponse>

    @POST("/api/login")
    suspend fun login(@Body request: LoginRequest):
            Response<LoginResponse>
}