package com.example.gamegallery.tabs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamegallery.R
import com.example.gamegallery.datos.CircleTransform
import com.example.gamegallery.domain.Juego
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class JuegoListAdapter (val listaJuegos: List<Juego>):
        RecyclerView.Adapter<JuegoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_juego, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindJuego(listaJuegos[position])
    }

    override fun getItemCount(): Int = listaJuegos.size


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        private val iconJuego = view.find<ImageView>(R.id.icon_juego)
        private val nombreJuego = view.find<TextView>(R.id.nombre_juego)
        fun bindJuego(juego: Juego){

            with(juego){
                val url = icon
                Picasso.with(itemView.context).load(url).transform(CircleTransform()).into(iconJuego)
                nombreJuego.text=nombre
            }

        }
    }

}