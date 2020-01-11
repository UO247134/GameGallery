package com.example.gamegallery.ui

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.gamegallery.R
import com.example.gamegallery.R.id
import com.example.gamegallery.datos.Datos
import com.example.gamegallery.domain.Info
import com.example.gamegallery.domain.Juego
import com.example.gamegallery.util.PlayerConfig
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_concrete_game.*
import java.text.SimpleDateFormat
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }
    private var parcel : Juego? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concrete_game)
        parcel = intent.extras?.getParcelable("Juego")

        addTextView(linLayout,parcel?.nombre,30.0f,true)

        addImageView(parcel?.icon)

        addVideoView(parcel?.videoUrl)

        addValueButtonsEvent(Info.usuarioActual.usuario,parcel?.id.toString())

        var consolas="\nConsolas: \n"
        parcel?.consola?.forEach { consola -> consolas+= "$consola - " }
        consolas=consolas.substring(0,consolas.length-3)
        addTextView(linLayout,consolas)

        val date = parcel?.fecha_lanzamiento
        var fecha = Date()
        if(date!=null){
            fecha=date
        }
        addTextView(linLayout,"\nFecha Lanzamiento: "+ SimpleDateFormat("dd-MM-yyyy",Locale("es","ES")).format(fecha))

        addTextView(linLayout,"\nGénero: "+parcel?.genero)

        var comentarios = "\nComentarios:\n"
        for (juego in Info.juegos){

            if(juego.nombre==parcel?.nombre){
                val comments = juego.comments
                if(comments!=null){
                    for(c in comments){
                        comentarios+= "-> $c\n"
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
        val btn  = Button(applicationContext)
        btn.text=getString(R.string.comentar)
        btn.setOnClickListener{crearComentario()}
        ll.addView(btn)
    }



    private fun crearComentario(){
        val intent = Intent(this.applicationContext, ComentarActivity::class.java)
        intent.putExtra("Juego", parcel)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.applicationContext.startActivity(intent)
    }

    private fun addTextView(ll : LinearLayout, text:String?,size: Float=20.0f,bold:Boolean=false):TextView{
        val tv = TextView(this)
        tv.text = text
        tv.textSize = size
        if(bold)
            tv.setTypeface(null,Typeface.BOLD)
        tv.textAlignment= View.TEXT_ALIGNMENT_CENTER
        ll.addView(tv)
        return tv
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
    private fun addValueButtonsEvent(user:String, gameID:String){
        val likeButton = findViewById<Button>(id.buttonLike)
        val dislikeButton = findViewById<Button>(id.buttonDislike)

        likeButton.setOnClickListener{
            makeVote(true,user,gameID)
        }

        dislikeButton.setOnClickListener{
            makeVote(false,user, gameID)
        }

    }
    private fun makeVote(vote: Boolean, user:String,gameID: String){
        val db = FirebaseFirestore.getInstance()

        db.collection("juegos")
                .get()
                .addOnSuccessListener { result ->
                    for (game in result) if(gameID == game.id){
                        val mapa : MutableMap<String,Boolean>? = game.get("valoraciones") as MutableMap<String,Boolean>
                        if (mapa != null) {
                            mapa[user] = vote
                            updatePoints(gameID,mapa)
                            Toast.makeText(this,"Voto emitido correctamente", Toast.LENGTH_SHORT).show()
                        }
                        break
                    }

                }
                .addOnFailureListener {
                    Toast.makeText(this,"Voto no ha podido ser emitido", Toast.LENGTH_SHORT).show()
                }

    }

    override fun onDestroy() {
        super.onDestroy()
        val nombre =parcel?.nombre
        if(nombre!=null)
        Info.postComentarios(nombre)
    }

    private fun updatePoints(id_doc: String, map: Map<String,Boolean>){

        val db = FirebaseFirestore.getInstance()
        val documento =db.collection("juegos").document(id_doc)
        documento.update("valoraciones",map)

    }

}
