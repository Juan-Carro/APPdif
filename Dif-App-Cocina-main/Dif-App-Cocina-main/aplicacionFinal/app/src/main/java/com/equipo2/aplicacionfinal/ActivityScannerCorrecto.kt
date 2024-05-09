package com.equipo2.aplicacionfinal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView


class ActivityScannerCorrecto : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_correcto)

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


        val switchDonacion = findViewById<Switch>(R.id.donacion)
        var donativo = 0 // Por defecto, asumiremos que el switch está desactivado

        switchDonacion.setOnCheckedChangeListener { _, isChecked ->
            donativo = if (isChecked) {
                1
            } else {
                0
            }
        }

        var valorSpinner = findViewById<Spinner>(R.id.spinner_condicion)

        // Crear un ArrayAdapter usando un array de strings y un layout predefinido
        var adapter = ArrayAdapter.createFromResource(
            this,
            R.array.opciones_array, // R.array.opciones_array es un array de strings en tu archivo de recursos
            android.R.layout.simple_spinner_item
        )

        // Especificar el layout que se usará cuando se despliegue el Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignar el ArrayAdapter al Spinner
        valorSpinner.adapter = adapter

        // Declarar la variable fuera del listener
        var valorSeleccionado = ""

        // Agregar un listener al Spinner para manejar los cambios de selección
        valorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Actualizar el valor de la variable con el valor seleccionado
                valorSeleccionado = parent.getItemAtPosition(position).toString()
                // Aquí puedes ver el valor seleccionado
                println("Valor seleccionado: $valorSeleccionado")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val volverMenu = findViewById<Button>(R.id.btn_menu)
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
            startActivity(Intent(this, ActivityScanner::class.java))
        }
        volverMenu.setOnClickListener{
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
        }
    }
}
