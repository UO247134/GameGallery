package com.example.gamegallery.domain

import android.annotation.SuppressLint
import com.example.gamegallery.datos.Datos

class Info {

    companion object {

        private var plataformas = listOf("PS4","Switch","Xbox","PC","3rd Party")
        var juegos: List<Juego> = ArrayList()
        var genero = "All"
        private var usuarios: MutableList<Usuario> = ArrayList()
        var usuarioActual = Usuario("default","none","none@none.com", ArrayList(plataformas))
        var valido=false

        fun setUsuario(user: String, password: String): Boolean{
            for(usuario in usuarios){
                if(usuario.usuario==user && usuario.contrasena==password){
                    actualizarPreferenciasUsuario()
                    usuarioActual=usuario
                    return true
                }

            }
            return false

        }

        fun updateUsuarios(usuarios:MutableList<Usuario>){
            this.usuarios=usuarios
        }

        fun getJuegos(nombreConsola: String): List<Juego> {
            var toRet : List<Juego> = ArrayList<Juego>(juegos)
            if (nombreConsola == "All" && genero == "All") { //Todas las consolas y géneros
                return ordenar(ArrayList<Juego>(juegos))
            }

            if(nombreConsola == "All") { //Todas las consolas
                toRet = toRet.filter { j -> j.genero == genero }
                return ordenar(toRet)
            }


            if(genero == "All") { //Todos los generos
                toRet = toRet.filter { j -> j.consola!!.contains(nombreConsola) }
                return ordenar(toRet)
            }
            else
            {
                toRet = toRet.filter { j -> j.consola!!.contains(nombreConsola) && j.genero==genero }
            }

            return ordenar(toRet)
        }

        private fun ordenar(lista : List<Juego>):List<Juego>{
            var toRet=lista.sortedBy { x -> x.fecha_lanzamiento.time }
            toRet = toRet.reversed()
            return toRet
        }

        fun updateJuegos(juegos: List<Juego>) {
            this.juegos = juegos
        }

        @SuppressLint("UseSparseArrays")
        fun getVisibles():MutableMap<Int,String>{
            val result : MutableMap<Int,String> = HashMap()
            result[0]="All"

            var counter=1

            for (plataforma in usuarioActual.consolas){
                   result[counter]=plataforma
                counter++
            }


            return result
        }
        fun existeUsuario( usuario: String):Boolean{
            usuarios.forEach { u -> if(u.usuario==usuario) return true }

            return false
        }

        fun addUsuario(usuario:Usuario){
            usuarios.add(usuario)
            Datos.addUsuario(usuario)
        }

        fun actualizarPreferenciasUsuario(){
            if(usuarios.contains(usuarioActual))
                Datos.actualizarPreferencias(usuarioActual)
        }

        fun cargarDatos(){
            Datos.getAllJuegos()
            Datos.getAllUsers()
        }

        fun addComentario(nombre: String,comentario:String){
            for (juego in juegos){
                if(juego.nombre==nombre){
                    juego.comments?.add(comentario)
                }
            }
        }

        fun postComentarios(nombreJuego:String){
            for (juego in juegos){
                if(juego.nombre==nombreJuego){
                    Datos.subirComentarios(nombreJuego,juego.comments)
                }
            }

        }
    }



}