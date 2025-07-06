package com.example.trabajofinal

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class HomeActivity : AppCompatActivity() {

    // Permiso para acceder al calendario
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            addEventToCalendar()
        } else {
            Toast.makeText(this, "Permiso denegado para acceder al calendario", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Botón "Ver mis eventos"
        findViewById<LinearLayout>(R.id.btnVerEventos).setOnClickListener {
            Toast.makeText(this, "Ver eventos clicado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EventosActivity::class.java))
        }

        // Botón "Calendario"
        findViewById<LinearLayout>(R.id.btnCalendario).setOnClickListener {
            Toast.makeText(this, "Calendario clicado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Calendario::class.java))
        }

        // Botón "Ajustes"
        findViewById<LinearLayout>(R.id.btnAgregarEvento).setOnClickListener {
            Toast.makeText(this, "Ajustes clicado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Ajustes::class.java))
        }

        // ✅ Botón "Agregar evento al calendario"
        findViewById<Button>(R.id.btnGoogleCalendar)?.setOnClickListener {
            checkCalendarPermission()
        }
    }

    private fun checkCalendarPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_CALENDAR
            ) == PackageManager.PERMISSION_GRANTED -> {
                addEventToCalendar()
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.WRITE_CALENDAR)
            }
        }
    }

    private fun addEventToCalendar() {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, "Título del evento")
            putExtra(CalendarContract.Events.EVENT_LOCATION, "Ubicación del evento")
            putExtra(CalendarContract.Events.DESCRIPTION, "Descripción del evento")
            // Opcional: start/end time, etc.
        }
        startActivity(intent)
    }
}
