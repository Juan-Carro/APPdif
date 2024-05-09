package com.equipo2.aplicacionfinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView

class ActivityAyudantes : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayudantes)

        val curp = intent.getStringExtra("curp")
        val apellido1 = intent.getStringExtra("apellido1")
        val apellido2 = intent.getStringExtra("apellido2")
        val nombreCompleto = intent.getStringExtra("nombreCompleto")
        val sexo = intent.getStringExtra("sexo")
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento")
        val pais = intent.getStringExtra("pais")
        val numero = intent.getStringExtra("numero")

        val textViewCurp = findViewById<TextView>(R.id.textViewCurp)
        val textViewApellido1 = findViewById<TextView>(R.id.textViewApellido1)
        val textViewApellido2 = findViewById<TextView>(R.id.textViewApellido2)
        val textViewNombreCompleto = findViewById<TextView>(R.id.textViewNombreCompleto)
        val textViewSexo = findViewById<TextView>(R.id.textViewSexo)
        val textViewFechaNacimiento = findViewById<TextView>(R.id.textViewFechaNacimiento)
        val textViewPais = findViewById<TextView>(R.id.textViewPais)
        val textViewNumero = findViewById<TextView>(R.id.textViewNumero)

        // Asignar valores a los TextViews
        textViewCurp.text = "CURP: $curp"
        textViewApellido1.text = "Apellido Paterno: $apellido1"
        textViewApellido2.text = "Apellido Materno: $apellido2"
        textViewNombreCompleto.text = "Nombre Completo: $nombreCompleto"
        textViewSexo.text = "Sexo: $sexo"
        textViewFechaNacimiento.text = "Fecha de Nacimiento: $fechaNacimiento"
        textViewPais.text = "Estado: $pais"
        textViewNumero.text = "Número: $numero"

        var donativo = 1

        var valorSeleccionado = "Ninguna"

        val menuButton = findViewById<Button>(R.id.btn_menu)
        val restartButton = findViewById<Button>(R.id.btnReiniciar)


        restartButton.setOnClickListener {
            Log.d("ActivityScannerCorrecto", "Datos recibidos:")
            Log.d("ActivityScannerCorrecto", "CURP: $curp")
            Log.d("ActivityScannerCorrecto", "Apellido1: $apellido1")
            Log.d("ActivityScannerCorrecto", "Apellido2: $apellido2")
            Log.d("ActivityScannerCorrecto", "NombreCompleto: $nombreCompleto")
            Log.d("ActivityScannerCorrecto", "Sexo: $sexo")
            Log.d("ActivityScannerCorrecto", "FechaNacimiento: $fechaNacimiento")
            Log.d("ActivityScannerCorrecto", "Estado: $pais")
            Log.d("ActivityScannerCorrecto", "Numero: $numero")
            Log.d("ActivityScannerCorrecto", "Spinner: $valorSeleccionado")
            Log.d("ActivityScannerCorrecto", "Donativo: $donativo")
            finish()
            startActivity(Intent(this, ActivityScannerAyudantes::class.java))

            finish()
        }
        menuButton.setOnClickListener{
            Log.d("ActivityScannerCorrecto", "Datos recibidos:")
            Log.d("ActivityScannerCorrecto", "CURP: $curp")
            Log.d("ActivityScannerCorrecto", "Apellido1: $apellido1")
            Log.d("ActivityScannerCorrecto", "Apellido2: $apellido2")
            Log.d("ActivityScannerCorrecto", "NombreCompleto: $nombreCompleto")
            Log.d("ActivityScannerCorrecto", "Sexo: $sexo")
            Log.d("ActivityScannerCorrecto", "FechaNacimiento: $fechaNacimiento")
            Log.d("ActivityScannerCorrecto", "Estado: $pais")
            Log.d("ActivityScannerCorrecto", "Numero: $numero")
            Log.d("ActivityScannerCorrecto", "Spinner: $valorSeleccionado")
            Log.d("ActivityScannerCorrecto", "Donativo: $donativo")
            finish()
            startActivity(Intent(this, ActivityMenuPrincipal::class.java))

            finish()
        }
    }
}