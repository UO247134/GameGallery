package com.example.gamegallery.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gamegallery.R
import com.example.gamegallery.domain.Info
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View){
        var usuario = txtUser.text.toString()
        var password = txtPassword.text.toString()

        if(Info.setUsuario(usuario,password)){
            Toast.makeText(this.applicationContext,"Sesión Iniciada",Toast.LENGTH_SHORT).show();
            this.finish()
        }
        else{
            Toast.makeText(this.applicationContext,"El usuario no está registrado",Toast.LENGTH_SHORT).show();
        }
    }

    fun crearRegistro(view: View){
        val intent = Intent(this.applicationContext, RegistroActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.applicationContext.startActivity(intent)

    }
}
