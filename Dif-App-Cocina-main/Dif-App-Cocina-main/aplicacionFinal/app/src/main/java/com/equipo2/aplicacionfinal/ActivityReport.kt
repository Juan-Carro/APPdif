package com.equipo2.aplicacionfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast

class ActivityReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val descripcionEditText = findViewById<EditText>(R.id.descripcion)
        val situacionSpinner = findViewById<Spinner>(R.id.spinner_situacion)
        val cierreTemporalSwitch = findViewById<Switch>(R.id.cierre_temporal)
        val enviarButton = findViewById<Button>(R.id.btnReiniciar)
        val menuButton = findViewById<Button>(R.id.btn_menu)


        var cierre = 0 // Por defecto, asumiremos que el switch está desactivado

        cierreTemporalSwitch.setOnCheckedChangeListener { _, isChecked ->
            cierre = if (isChecked) {
                1
            } else {
                0
            }
        }

        var valorSpinner = findViewById<Spinner>(R.id.spinner_situacion)

        // Crear un ArrayAdapter usando un array de strings y un layout predefinido
        var adapter = ArrayAdapter.createFromResource(
            this,
            R.array.tipos_cierre, // R.array.opciones_array es un array de strings en tu archivo de recursos
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

        enviarButton.setOnClickListener {

            val situacionSeleccionada = situacionSpinner.selectedItem.toString()
            val descripcion = descripcionEditText.text.toString()
            val cierreTemporal = cierreTemporalSwitch.isChecked


            Log.d("ActivityReport", "Situación Seleccionada: $situacionSeleccionada")
            Log.d("ActivityReport", "Descripción: $descripcion")
            Log.d("ActivityReport", "Cierre Temporal: $cierreTemporal")
            Toast.makeText(this, "Reporte Enviado", Toast.LENGTH_SHORT).show()
        }

        // Ahora puedes utilizar los datos como desees

        menuButton.setOnClickListener {
            // Aquí puedes agregar el código que se ejecuta al hacer clic en el botón "MENU PRINCIPAL"
            val intent = Intent(this, ActivityMenuPrincipal::class.java)
            startActivity(intent)
            finish()
        }
    }
}
