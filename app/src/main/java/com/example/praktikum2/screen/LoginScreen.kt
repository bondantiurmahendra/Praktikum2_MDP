package com.example.praktikum2.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praktikum2.model.request.LoginRequest
import com.example.praktikum2.navigation.Screen
import com.example.praktikum2.service.api.ApiClient
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .imePadding(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = false
            },
            isError = usernameError,
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        if (usernameError) {
            Text(
                "Username tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            isError = passwordError,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        if (passwordError) {
            Text(
                "Password tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                usernameError = username.isBlank()
                passwordError = password.isBlank()

                if (!usernameError && !passwordError) {
                    isLoading = true
                    coroutineScope.launch {
                        try {
                            val response = ApiClient.instance.login(
                                LoginRequest(username = username, password = password)
                            )
                            isLoading = false
                            val body = response.body()

                            if (response.isSuccessful && body?.code == 200) {
                                Toast.makeText(context, "Login berhasil!", Toast.LENGTH_SHORT).show()
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            } else {
                                val errorMessage = body?.message ?: response.message()
                                Toast.makeText(context, "Gagal: $errorMessage", Toast.LENGTH_LONG).show()
                            }
                        } catch (e: Exception) {
                            isLoading = false
                            Toast.makeText(context, "Terjadi kesalahan: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { navController.navigate(Screen.Register.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Belum punya akun? Daftar")
        }
    }

    if (isLoading) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {},
            title = { Text("Loading") },
            text = {
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Sedang login...")
                }
            }
        )
    }
}