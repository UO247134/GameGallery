package com.example.gamegallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gamegallery.R
import com.example.gamegallery.domain.Info
import com.example.gamegallery.domain.Usuario
import kotlinx.android.synthetic.main.activity_registro.*
import org.jetbrains.anko.toast

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
    }

    fun registrarse(view : View){
        var correo = txtCorreoReg.text.toString()
        var usuario = txtUserReg.text.toString()
        var password = txtPasswordReg.text.toString()
        var repPassword = txtRepetirPasswordReg.text.toString()

        if(password!=repPassword)
          view.context.toast("Las contraseñas deben ser iguales")
        else if(correo.isEmpty() || !correo.contains("@"))
            view.context.toast("El correo no es valido")
        else if (usuario.isEmpty() || !Info.existeUsuario(usuario))
            view.context.toast("El usuario no puede estar vacío y no puede existir ya")
        else
        {
            var u = Usuario(usuario,password,correo,ArrayList())
            Info.addUsuario(u);
            this.finish();
        }
    }
}
