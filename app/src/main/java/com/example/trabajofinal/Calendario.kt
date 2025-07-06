package com.example.trabajofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class Calendario : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var btnVerTodos: Button
    private lateinit var subtituloMes: TextView

    // Launcher para el intent de Google Sign-In
    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        handleSignInResult(task)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        // Inicializa vistas
        btnVerTodos = findViewById(R.id.btnVerTodos)
        subtituloMes = findViewById(R.id.subtituloMes)

        // Configurar opciones de inicio de sesión con tu CLIENT_ID (usa requestEmail si no necesitas más)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Botón: Iniciar sesión con Google
        btnVerTodos.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            signInLauncher.launch(signInIntent)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)

            // ¡Inicio de sesión exitoso!
            val nombreUsuario = account?.displayName
            val correo = account?.email

            Toast.makeText(this, "Bienvenido, $nombreUsuario", Toast.LENGTH_LONG).show()
            subtituloMes.text = "Eventos de: $nombreUsuario"
            Log.d("Calendario", "Correo: $correo")

        } catch (e: ApiException) {
            Toast.makeText(this, "Error al iniciar sesión: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            Log.e("Calendario", "SignIn failed", e)
        }
    }
}
