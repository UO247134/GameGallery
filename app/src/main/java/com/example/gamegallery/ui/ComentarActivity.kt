package com.example.gamegallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gamegallery.R
import com.example.gamegallery.domain.Info
import com.example.gamegallery.domain.Juego
import kotlinx.android.synthetic.main.activity_comentar.*

class ComentarActivity : AppCompatActivity() {

    var juego : Juego? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentar)
        juego = intent.extras?.getParcelable("Juego")

    }

    fun comentar(view : View){
        var nombre = juego?.nombre;
        if(nombre!=null)
            Info.addComentario(nombre,txtComentario.text.toString())
        this.finish()
    }
}
