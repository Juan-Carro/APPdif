package com.equipo2.mlkitscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciar = findViewById<Button>(R.id.iniciarApp)

        btnIniciar.setOnClickListener{
                    val intent = Intent(this, ReinicioActivity::class.java)
                    startActivity(intent)
        }
    }
}



