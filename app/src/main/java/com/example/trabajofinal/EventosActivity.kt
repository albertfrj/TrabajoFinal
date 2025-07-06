package com.example.trabajofinal

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajofinal.data.AppDatabase
import com.example.trabajofinal.data.Event
import com.example.trabajofinal.data.EventDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventosActivity : AppCompatActivity() {

    private lateinit var tituloInput: EditText
    private lateinit var descripcionInput: EditText
    private lateinit var fechaInput: EditText
    private lateinit var horaInput: EditText
    private lateinit var guardarBtn: Button
    private lateinit var listaEventos: RecyclerView
    private lateinit var adapter: EventoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)

        val db = AppDatabase.getDatabase(this)
        val dao = db.eventDao()

        // Enlazar vistas
        tituloInput = findViewById(R.id.editTitulo)
        descripcionInput = findViewById(R.id.editDescripcion)
        fechaInput = findViewById(R.id.editFecha)
        horaInput = findViewById(R.id.editHora)
        guardarBtn = findViewById(R.id.btnGuardar)
        listaEventos = findViewById(R.id.recyclerEventos)

        adapter = EventoAdapter()
        listaEventos.layoutManager = LinearLayoutManager(this)
        listaEventos.adapter = adapter

        guardarBtn.setOnClickListener {
            val nuevoEvento = Event(
                titulo = tituloInput.text.toString(),
                descripcion = descripcionInput.text.toString(),
                fecha = fechaInput.text.toString(),
                hora = horaInput.text.toString()
            )

            lifecycleScope.launch {
                dao.insertar(nuevoEvento)
            }

            limpiarCampos()
        }

        // Este launch es suficiente
        lifecycleScope.launch {
            cargarEventos(dao)
        }
    }

    // No necesitas marcarla como suspend
    private suspend fun cargarEventos(dao: EventDao) {
        dao.obtenerTodos().collect { lista ->
            Log.d("EventosActivity", "Cargando ${lista.size} eventos...")
            adapter.setData(lista)
        }
    }

    private fun limpiarCampos() {
        tituloInput.text.clear()
        descripcionInput.text.clear()
        fechaInput.text.clear()
        horaInput.text.clear()
    }
}
