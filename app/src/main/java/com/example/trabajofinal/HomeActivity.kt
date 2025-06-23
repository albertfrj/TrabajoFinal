package com.example.trabajofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajofinal.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVerEventos.setOnClickListener {
            Toast.makeText(this, "Ver eventos clicado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Catalog::class.java)
            startActivity(intent)
            }



        binding.btnCalendario.setOnClickListener {
            Toast.makeText(this, "Calendario clicado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Calendario::class.java)
            startActivity(intent)
        }

        binding.btnAgregarEvento.setOnClickListener {
            Toast.makeText(this, "Ajustes clicado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Ajustes::class.java)
            startActivity(intent)
        }
    }
}
