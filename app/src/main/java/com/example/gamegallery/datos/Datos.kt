package com.example.gamegallery.datos

import android.content.ContentValues.TAG
import android.util.Log
import com.example.gamegallery.domain.Juego

import com.google.firebase.firestore.FirebaseFirestore

class Datos (){



    companion object {
        fun getJuegos(nombreConsola:String) : List<Juego>{
            val db = FirebaseFirestore.getInstance()

            db.collection("juegos")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d(TAG, "${document.id} => ${document.data}")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents.", exception)
                    }

            val juegos : List<Juego> = listOf(Juego("PES 2020","https://sm.ign.com/ign_es/screenshot/default/blob_hags.png","PS4"),
                    Juego("Dark Souls 3","https://i.blogs.es/984dc1/cc76dfed12ccb88d8abcc327922f3c15/450_1000.png","3rd Party"))
            if(nombreConsola.equals("All"))
                return juegos
            var toRet = juegos.filter { juego -> juego.consola.equals(nombreConsola) }
            return toRet
        }
    }


}