package com.equipo2.aplicacionfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    //Las variables que se toman desde el activity_main LOGIN
    var usuarioEditText: TextInputEditText? = null
    var contrasenaEditText: TextInputEditText? = null
    var btnIniciar: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //Referencias al contenido ingresado
        usuarioEditText = findViewById(R.id.usuario)
        contrasenaEditText = findViewById(R.id.Contrasena)
        btnIniciar = findViewById(R.id.btnLogin)

        //accion del boton
        btnIniciar?.setOnClickListener {login()}


    }

    private fun login() {
        //valores como inmutables
        val usuario = usuarioEditText?.text.toString()
        val contrasena = contrasenaEditText?.text.toString()

        if (isValidUsuario(usuario, contrasena)) {
            Toast.makeText(this,"Iniciando...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ActivityMenuPrincipal::class.java)
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "El formulario es incorrecto", Toast.LENGTH_LONG).show()
        }
    }

    private fun isValidUsuario(usuario: String, contrasena: String): Boolean {
        if (usuario.isBlank()) {
            return false
        }
        if (contrasena.isBlank()) {
            return false
        }
        return true
    }

}

