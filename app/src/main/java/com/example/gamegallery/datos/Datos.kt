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
            var juegos = ArrayList<Juego>()

            val db = FirebaseFirestore.getInstance()

            db.collection("juegos")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            var id = "${document.id}"
                            var nombre = document.get("nombre").toString()

                            var icon = document.get("icon").toString()
                            @Suppress("UNCHECKED_CAST")
                            var consola: List<String>? = document.get("consola") as List<String>?
                            var consolas : ArrayList<String> = ArrayList()
                            if(consola!=null)
                                for (con in consola){
                                    consolas.add(con)
                                }

                            var videoUrl = document.get("videoUrl").toString()
                            @Suppress("UNCHECKED_CAST")
                            var comment : List<String> = document.get("comments") as List<String>
                            var comments : ArrayList<String> = ArrayList()
                                for (com in comment){
                                    comments.add(com)
                                }

                            var point : List<Boolean>? = document.get("points") as? List<Boolean>
                            var points : List<Boolean> = ArrayList()
                            if(point!=null)
                                points=point

                            var genero = document.get("genero").toString()

                            var mapa : Map<String,Boolean>? = document.get("valoraciones") as? Map<String,Boolean>
                            var puntos = 666;

                            if(mapa!=null){
                                var map: Map<String,Boolean> = mapa
                                var votos: MutableList<Boolean> = ArrayList()
                                for (key in map.keys){
                                    var bool: Boolean? = map[key]
                                    if(bool!=null)
                                        votos.add(bool);

                                }
                                puntos=calculateTotalPoints(votos)
                            }


                            var fecha_lanzamiento : Timestamp = document.get("fecha_lanzamiento") as Timestamp
                            var fecha_lanz : Date = fecha_lanzamiento.toDate()

                            var j = Juego(nombre,icon,consolas,genero,videoUrl,comments, puntos,fecha_lanz,id)
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

            if(votes.size==0)
                return retorno
            for (vote in votes){
                if(vote){
                    retorno++
                }
            }

            return 100*retorno/votes.size
        }
        fun getAllUsers(){
            var usuarios = ArrayList<Usuario>()

            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            var user = document.get("usuario").toString()
                            var password = document.get("password").toString()
                            var correo = document.get("correo").toString()
                            var consola: MutableList<String>? = document.get("consolas") as? MutableList<String>
                            var consolas : MutableList<String> = ArrayList()
                            if(consola!=null)
                                consolas=consola

                            var u = Usuario(user,password,correo,consolas)
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
                    "password" to usuario.contraseña,
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
                        throw Exception()
                    }
        }

        fun actualizarPreferencias(usuario: Usuario){
            var id_doc = ""
            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            var id = "${document.id}"
                            var user = document.get("usuario").toString()
                            if(user==usuario.usuario){
                                id_doc=id
                                break
                            }

                        }
                        actualizarDocumento(id_doc,usuario)
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

        public fun subirComentarios(nombreJuego:String,comentarios:List<String>?){
            var id_doc = ""
            val db = FirebaseFirestore.getInstance()

            db.collection("juegos")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            var id = "${document.id}"
                            var nombre = document.get("nombre").toString()
                            if(nombre==nombreJuego){
                                id_doc=id
                                break
                            }

                        }
                        actualizarDocumentoComentarios(id_doc,comentarios)
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