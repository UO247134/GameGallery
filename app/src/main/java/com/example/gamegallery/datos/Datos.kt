package com.example.gamegallery.datos

import android.content.ContentValues.TAG
import android.util.Log
import com.example.gamegallery.domain.Info
import com.example.gamegallery.domain.Juego

import com.google.firebase.firestore.FirebaseFirestore

class Datos {



    companion object {
        fun getAllJuegos() {
            var juegos = ArrayList<Juego>()

            val db = FirebaseFirestore.getInstance()

            db.collection("juegos")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            var nombre = document.get("nombre").toString()

                            var icon = document.get("icon").toString()

                            var consola: List<String>? = document.get("consola") as? List<String>
                            var consolas : List<String> = ArrayList()
                            if(consola!=null)
                                consolas=consola

                            var videoUrl = document.get("videoUrl").toString()

                            var comment : List<String>? = document.get("comments") as? List<String>
                            var comments : List<String> = ArrayList()
                            if(comment!=null)
                                comments=comment

                            var point : List<Boolean>? = document.get("points") as? List<Boolean>
                            var points : List<Boolean> = ArrayList()
                            if(point!=null)
                                points=point

                            var genero = document.get("genero").toString()
                            var j = Juego(nombre,icon,consolas,genero,videoUrl,comments,points)
                            juegos.add(j)

                        }
                        Info.updateJuegos(juegos)
                    }
                    .addOnFailureListener { exception ->
                       throw exception
                    }


        }
    }


}