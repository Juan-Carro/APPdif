package com.equipo2.aplicacionfinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class ActivityMenuPrincipal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val cardScanner = findViewById<CardView>(R.id.card_qr)
        val cardScannerAyudantes = findViewById<CardView>(R.id.card_qrayudantes)
        val cardReportes = findViewById<CardView>(R.id.card_reportar)
        val cardLogOut =findViewById<CardView>(R.id.card_logout)
        val cardInventario = findViewById<CardView>(R.id.card_inventario)

        cardScanner.setOnClickListener{
            val intent = Intent(this, ActivityScanner::class.java)
            startActivity(intent)
        }
        cardScannerAyudantes.setOnClickListener(){
            val intent = Intent(this, ActivityScannerAyudantes::class.java)
            startActivity(intent)
        }
        cardReportes.setOnClickListener{
            val intent = Intent(this, ActivityReport::class.java)
            startActivity(intent)
        }
        cardLogOut.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        cardInventario.setOnClickListener{
            val intent = Intent(this, ActivityInventario::class.java)
            startActivity(intent)
        }
    }
}