package com.example.gamegallery.domain

import com.example.gamegallery.datos.Datos

class Info {

    companion object {

        var juegos: List<Juego> = ArrayList<Juego>()
        var genero = "All";


        fun getJuegos(nombreConsola: String): List<Juego> {
            if (juegos.size == 0) {
                Datos.getAllJuegos()
                Thread.sleep(1000)
            }

            var toRet : List<Juego> = ArrayList<Juego>(juegos);
            if (nombreConsola.equals("All") && genero.equals("All")) //Todas las consolas y géneros
                return ArrayList<Juego>(juegos)

            if(nombreConsola.equals("All")) { //Todas las consolas
                toRet = toRet.filter { j -> j.genero == genero }
                return toRet
            }


            if(genero.equals("All")) { //Todos los generos
                toRet = toRet.filter { j -> j.consola.contains(nombreConsola) }
                return toRet
            }
            else
            {
                toRet = toRet.filter { j -> j.consola.contains(nombreConsola) && j.genero==genero }
            }

            return toRet
        }

        fun updateJuegos(juegos: List<Juego>) {
            this.juegos = juegos
        }


    }



}