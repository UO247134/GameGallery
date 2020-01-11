package com.example.gamegallery.ui

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.gamegallery.R
import com.example.gamegallery.R.id
import com.example.gamegallery.domain.Info
import com.example.gamegallery.domain.Juego
import com.example.gamegallery.util.PlayerConfig
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_concrete_game.*
import java.text.SimpleDateFormat

class SecondActivity : AppCompatActivity() {

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }
    var parcel : Juego? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concrete_game)
        parcel = intent.extras?.getParcelable<Juego>("Juego")

        addTextView(linLayout,parcel?.nombre,30.0f,true)

        addImageView(parcel?.icon)

        addVideoView(parcel?.videoUrl)

        var consolas="\nConsolas: \n";
        parcel?.consola?.forEachIndexed { index, consola -> consolas+=consola+" - "}
        consolas=consolas.substring(0,consolas.length-3)
        addTextView(linLayout,consolas)

        var fecha = parcel?.fecha_lanzamiento
        addTextView(linLayout,"\nFecha Lanzamiento: "+ SimpleDateFormat("dd-MM-yyyy").format(fecha));

        addTextView(linLayout,"\nGénero: "+parcel?.genero)

        var comentarios = "\nComentarios:\n";
        for (juego in Info.juegos){

            if(juego.nombre==parcel?.nombre){
                var comments = juego.comments
                if(comments!=null){
                    for(c in comments){
                        comentarios+="-> "+c+"\n"
                    }
                }
            }
        }


        addTextView(linLayout,comentarios)
        addBotonComentario(linLayout)

        addTextView(linLayout,"\nPuntuacion: "+parcel?.points)

        addTextView(linLayout,"\n\n\n")
    }

    private fun addBotonComentario(ll: LinearLayout){
        var btn : Button = Button(applicationContext)
        btn.text=getString(R.string.comentar)
        btn.setOnClickListener{view -> crearComentario()}
        ll.addView(btn)
    }

    private fun crearComentario(){
        val intent = Intent(this.applicationContext, ComentarActivity::class.java)
        intent.putExtra("Juego", parcel)
        this.applicationContext.startActivity(intent)
    }

    private fun addTextView(ll : LinearLayout, text:String?,size: Float=20.0f,bold:Boolean=false){
        val tv = TextView(this)
        tv.text = text
        tv.textSize = size
        if(bold)
            tv.setTypeface(null,Typeface.BOLD)
        tv.textAlignment= View.TEXT_ALIGNMENT_CENTER
        ll.addView(tv)

    }
    private fun addVideoView(url:String?){
        val playButton = findViewById<Button>(id.ButtonPlay)
        playButton.setOnClickListener {
                val intent = YouTubeStandalonePlayer.createVideoIntent(this,PlayerConfig.API_KEY,url,
                        0, true, true)
                startActivity(intent)
        }

    }
    private fun addImageView(icon: String?){

        Picasso.with(this).load(icon).into(gameLogo)

    }
    private fun addValueButtonsEvent(){
        val likeButton = findViewById<Button>(id.buttonLike)
        val dislikeButton = findViewById<Button>(id.buttonDislike)

        likeButton.setOnClickListener{
            makeVote(true)
        }

        dislikeButton.setOnClickListener{
            makeVote(false)
        }

    }
    private fun makeVote(vote: Boolean){

        val db = FirebaseFirestore.getInstance()
        val game =db.collection("juegos")
    }

    override fun onDestroy() {
        super.onDestroy()
        var nombre =parcel?.nombre
        if(nombre!=null)
        Info.postComentarios(nombre)
    }
}
