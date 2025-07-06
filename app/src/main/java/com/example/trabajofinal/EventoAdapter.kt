package com.example.trabajofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajofinal.data.Event

class EventoAdapter : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    private var listaEventos: List<Event> = emptyList()

    fun setData(eventos: List<Event>) {
        listaEventos = eventos
        notifyDataSetChanged()
    }

    class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.textTitulo)
        val descripcion: TextView = view.findViewById(R.id.textDescripcion)
        val fechaHora: TextView = view.findViewById(R.id.textFechaHora)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evento, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = listaEventos[position]
        holder.titulo.text = evento.titulo
        holder.descripcion.text = evento.descripcion
        holder.fechaHora.text = "${evento.fecha} ${evento.hora}"
    }

    override fun getItemCount(): Int = listaEventos.size
}
