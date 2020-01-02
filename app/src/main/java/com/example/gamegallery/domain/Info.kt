package com.example.gamegallery.domain

import com.example.gamegallery.datos.Datos

class Info {

    companion object {

        var plataformas = listOf("PS4","Switch","Xbox","PC","3rd Party")
        var juegos: List<Juego> = ArrayList<Juego>()
        var genero = "All";
        var plataformasAMostrar : MutableMap<String,Boolean> = mutableMapOf(plataformas[0] to true, plataformas[1] to true, plataformas[2] to true, plataformas[3] to true, plataformas[4] to true) //Todas se muestran por defecto

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

        fun getVisibles():MutableMap<Int,String>{
            var result : MutableMap<Int,String> = HashMap<Int,String>()
            result[0]="All";

            var counter=1;

           for (plataforma in plataformasAMostrar.keys){
               if(plataformasAMostrar[plataforma]==true){
                   result[counter]=plataforma;
                   counter++;
               }

           }
            return result;
        }

    }



}