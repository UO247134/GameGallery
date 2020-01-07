package com.example.gamegallery.domain

import com.example.gamegallery.datos.Datos

class Info {

    companion object {

        var plataformas = listOf("PS4","Switch","Xbox","PC","3rd Party")
        var juegos: List<Juego> = ArrayList<Juego>()
        var genero = "All"
        var usuarios: MutableList<Usuario> = ArrayList<Usuario>();
        var usuarioActual = Usuario("default","none","none@none.com", ArrayList(plataformas))
        var valido=false;

        fun setUsuario(user: String, password: String): Boolean{
            for(usuario in usuarios){
                if(usuario.usuario==user && usuario.contraseña==password){
                    actualizarPreferenciasUsuario()
                    usuarioActual=usuario
                    return true
                }

            }
            return false;

        }

        fun updateUsuarios(usuarios:MutableList<Usuario>){
            this.usuarios=usuarios;
        }

        fun getJuegos(nombreConsola: String): List<Juego> {
            var toRet : List<Juego> = ArrayList<Juego>(juegos);
            if (nombreConsola.equals("All") && genero.equals("All")) //Todas las consolas y géneros
                return ArrayList<Juego>(juegos)

            if(nombreConsola.equals("All")) { //Todas las consolas
                toRet = toRet.filter { j -> j.genero == genero }
                return toRet
            }


            if(genero.equals("All")) { //Todos los generos
                toRet = toRet.filter { j -> j.consola!!.contains(nombreConsola) }
                return toRet
            }
            else
            {
                toRet = toRet.filter { j -> j.consola!!.contains(nombreConsola) && j.genero==genero }
            }

            return toRet
        }

        fun updateJuegos(juegos: List<Juego>) {
            this.juegos = juegos
        }

        fun getVisibles():MutableMap<Int,String>{
            var result : MutableMap<Int,String> = HashMap<Int,String>()
            result[0]="All";

            var counter=1;

           for (plataforma in usuarioActual.consolas){
                   result[counter]=plataforma;
                   counter++;
               }


            return result;
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
    }



}