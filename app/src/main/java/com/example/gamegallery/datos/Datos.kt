package com.example.gamegallery.datos

import android.content.ContentValues.TAG
import android.util.Log
import com.example.gamegallery.domain.Info
import com.example.gamegallery.domain.Juego

import com.google.firebase.firestore.FirebaseFirestore

class Datos {



    companion object {
        fun getAllJuegos() {
            var juegos = ArrayList<Juego>();

            val db = FirebaseFirestore.getInstance()

            db.collection("juegos")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            var nombre = document.get("nombre").toString()
                            var icon = document.get("icon").toString()
                            var consola: List<String>? = document.get("consola") as? List<String>
                            var consolas : List<String> = ArrayList<String>()
                            if(consola!=null)
                                consolas=consola;

                            var genero = document.get("genero").toString()
                            var j = Juego(nombre,icon,consolas,genero)
                            juegos.add(j)

                        }
                        Info.updateJuegos(juegos);
                    }
                    .addOnFailureListener { exception ->
                       throw exception
                    }


        }
    }


}