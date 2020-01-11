package com.example.gamegallery.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gamegallery.R
import com.example.gamegallery.domain.Info
import com.example.gamegallery.domain.Juego
import kotlinx.android.synthetic.main.activity_comentar.*

class ComentarActivity : AppCompatActivity() {

    private var juego : Juego? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentar)
        juego = intent.extras?.getParcelable("Juego")

    }


    @Suppress("UNUSED_PARAMETER")
    fun comentar(view : View){
        val nombre = juego?.nombre
        if(nombre!=null)
            Info.addComentario(nombre,txtComentario.text.toString())
        this.finish()
    }
}
