package com.example.praktikum2.model.request

import android.provider.ContactsContract.CommonDataKinds.Email

data class RegisterRequest(
    val nm_lengkap: String,
    val email: String,
    val username: String,
    val password: String,
)
