package com.example.gamegallery.datos

import android.content.ContentValues.TAG
import android.util.Log
import com.example.gamegallery.domain.Info
import com.example.gamegallery.domain.Juego
import com.example.gamegallery.domain.Usuario
import com.google.firebase.Timestamp

import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class Datos {



    companion object {
        fun getAllJuegos() {
            val juegos = ArrayList<Juego>()

            val db = FirebaseFirestore.getInstance()

            db.collection("juegos")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val id = document.id
                            val nombre = document.get("nombre").toString()

                            val icon = document.get("icon").toString()
                            @Suppress("UNCHECKED_CAST")
                            val consola: List<String>? = document.get("consola") as List<String>?
                            val consolas : ArrayList<String> = ArrayList()
                            if(consola!=null)
                                for (con in consola){
                                    consolas.add(con)
                                }

                            val videoUrl = document.get("videoUrl").toString()
                            @Suppress("UNCHECKED_CAST")
                            val comment : List<String> = document.get("comments") as List<String>
                            val comments : ArrayList<String> = ArrayList()
                                for (com in comment){
                                    comments.add(com)
                                }


                            val genero = document.get("genero").toString()

                            val mapa : Map<String,Boolean>? = document.get("valoraciones") as Map<String,Boolean>
                            var puntos = 666

                            if(mapa!=null){
                                val map: Map<String,Boolean> = mapa
                                val votos: MutableList<Boolean> = ArrayList()
                                for (key in map.keys){
                                    val bool: Boolean? = map[key]
                                    if(bool!=null)
                                        votos.add(bool)

                                }
                                puntos=calculateTotalPoints(votos)
                            }


                            val fechaLanzamiento : Timestamp = document.get("fecha_lanzamiento") as Timestamp
                            val fechaLanz : Date = fechaLanzamiento.toDate()

                            val j = Juego(nombre,icon,consolas,genero,videoUrl,comments, puntos,fechaLanz,id)
                            juegos.add(j)

                        }
                        Info.updateJuegos(juegos)
                    }
                    .addOnFailureListener { exception ->
                        throw exception
                    }


        }
        private fun calculateTotalPoints(votes : List<Boolean>): Int {
            var retorno = 0

            if(votes.isEmpty())
                return retorno
            for (vote in votes){
                if(vote){
                    retorno++
                }
            }

            return 100*retorno/votes.size
        }
        fun getAllUsers(){
            val usuarios = ArrayList<Usuario>()

            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val user = document.get("usuario").toString()
                            val password = document.get("password").toString()
                            val correo = document.get("correo").toString()
                            val consola: MutableList<String>? = document.get("consolas") as MutableList<String>
                            var consolas : MutableList<String> = ArrayList()
                            if(consola!=null)
                                consolas=consola

                            val u = Usuario(user,password,correo,consolas)
                            usuarios.add(u)

                        }
                        Info.updateUsuarios(usuarios)
                    }
                    .addOnFailureListener { exception ->
                        throw exception
                    }


        }

        fun addUsuario(usuario:Usuario){
            val data = hashMapOf(
                    "correo" to usuario.correo,
                    "password" to usuario.contrasena,
                    "usuario" to usuario.usuario,
                    "consolas" to usuario.consolas
            )


            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        throw Exception(e)
                    }
        }

        fun actualizarPreferencias(usuario: Usuario){
            var idDoc = ""
            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val id = document.id
                            val user = document.get("usuario").toString()
                            if(user==usuario.usuario){
                                idDoc=id
                                break
                            }

                        }
                        actualizarDocumento(idDoc,usuario)
                    }
                    .addOnFailureListener { exception ->
                        throw exception
                    }


        }

        private fun actualizarDocumento(id_doc:String,usuario: Usuario){

            val db = FirebaseFirestore.getInstance()
            val documento =db.collection("users").document(id_doc)
            documento.update("consolas",usuario.consolas)

        }

        fun subirComentarios(nombreJuego:String,comentarios:List<String>?){
            var idDoc = ""
            val db = FirebaseFirestore.getInstance()

            db.collection("juegos")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val id = document.id
                            val nombre = document.get("nombre").toString()
                            if(nombre==nombreJuego){
                                idDoc=id
                                break
                            }

                        }
                        actualizarDocumentoComentarios(idDoc,comentarios)
                    }
                    .addOnFailureListener { exception ->
                        throw exception
                    }
        }

        private fun actualizarDocumentoComentarios(id_doc:String,comentarios:List<String>?){

            val db = FirebaseFirestore.getInstance()
            val documento =db.collection("juegos").document(id_doc)
            documento.update("comments",comentarios)

        }


    }


}