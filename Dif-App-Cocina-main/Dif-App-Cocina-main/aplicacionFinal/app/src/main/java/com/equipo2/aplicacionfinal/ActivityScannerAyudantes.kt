package com.equipo2.aplicacionfinal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.moduleinstall.InstallStatusListener
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class ActivityScannerAyudantes : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moduleInstallClient = ModuleInstall.getClient(this)
        val optionalModuleApi = GmsBarcodeScanning.getClient(this)

        // Inicializa el escáner
        leerQR()
        val scanner = GmsBarcodeScanning.getClient(this)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                // Tarea completada con éxito
                val barcodeValue = barcode.rawValue.toString()
                val isValidFormat = isValidBarcodeFormat(barcodeValue)


                if (isValidFormat != null && isValidFormat.size >= 8) {
                    val barcodeValue = barcode.rawValue.toString()
                    val isValidFormat = isValidBarcodeFormat(barcodeValue)

                    if (isValidFormat != null && isValidFormat.size >= 8) {
                        val curp = isValidFormat[0]
                        val apellido1 = isValidFormat[1]
                        val apellido2 = isValidFormat[2]
                        val nombreCompleto = isValidFormat[3]
                        val sexo = isValidFormat[4]
                        val fechaNacimiento = isValidFormat[5]
                        val pais = isValidFormat[6]
                        val numero = isValidFormat[7]

                        val intent = Intent(this, ActivityAyudantes::class.java).apply {
                            putExtra("curp", curp)
                            putExtra("apellido1", apellido1)
                            putExtra("apellido2", apellido2)
                            putExtra("nombreCompleto", nombreCompleto)
                            putExtra("sexo", sexo)
                            putExtra("fechaNacimiento", fechaNacimiento)
                            putExtra("pais", pais)
                            putExtra("numero", numero)
                        }
                        startActivity(intent)
                        finish()
                    }


                } else {
                    // El código no tiene el formato correcto
                    setContentView(R.layout.activity_scanner_ayudantes)
                    val textView = findViewById<TextView>(R.id.msgError)
                    textView.text = "EL QR ESCANEADO NO ES VÁLIDO"
                    val restartButton = findViewById<Button>(R.id.btn_reinicio)
                    restartButton.setOnClickListener {
                        finish()
                        startActivity(Intent(this, ActivityScannerAyudantes::class.java))

                    }
                }
            }
            .addOnCanceledListener {
                startActivity(Intent(this, ActivityMenuPrincipal::class.java))
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al iniciar el scanner", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ActivityMenuPrincipal::class.java))
            }


        //COMPROBAR E INSTALAR LOS MODULOS EN CASO DE SER NECESARIO
        moduleInstallClient
            .areModulesAvailable(optionalModuleApi)
            .addOnSuccessListener {
                if (it.areModulesAvailable()) {
                    println("<SI está instalado")
                    leerQR()
                } else {
                    println("No está instalado")
                    instalarModulo()
                }
            }
            .addOnFailureListener {
                println("Error al verificar módulo")
            }

    }

    private fun isValidBarcodeFormat(cadena: String): Array<String>? {
        val partes = cadena.split("||")
        if (partes.size != 2) {
            return null
        }
        val curp = partes[0]
        val resto = partes[1]
        val campos = resto.split("|")
        if (campos.size != 8) {
            return null
        }

        val apellido1 = campos[0]
        val apellido2 = campos[1]
        val nombreCompleto = campos[2]
        val sexo = campos[3]
        val fechaNacimiento = campos[4]
        val pais = campos[5]
        val numero = campos[6]

        return arrayOf(curp, apellido1, apellido2, nombreCompleto, sexo, fechaNacimiento, pais, numero)
    }


    private fun instalarModulo() {
        val moduleInstallClient = ModuleInstall.getClient(this)
        val optionalModuleApi = GmsBarcodeScanning.getClient(this)
        val moduleInstallRequest =
            ModuleInstallRequest.newBuilder()
                .addApi(optionalModuleApi)
                .setListener(listener)
                .build()

        moduleInstallClient
            .installModules(moduleInstallRequest)
            .addOnSuccessListener {
                if (it.areModulesAlreadyInstalled()) {
                    // Modules are already installed when the request is sent.
                    println("I N S T A L A D O ......")
                }
            }
            .addOnFailureListener {
                println("NO SE PUEDE INSTALAR")
            }

    }

    private fun leerQR() {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
            )
            .enableAutoZoom()
            .build()
    }

    inner class ModuleInstallProgressListener : InstallStatusListener {
        override fun onInstallStatusUpdated(update: ModuleInstallStatusUpdate) {
            // Progress info is only set when modules are in the progress of downloading.
            update.progressInfo?.let {
                val progress = (it.bytesDownloaded * 100 / it.totalBytesToDownload).toInt()
                // Set the progress for the progress bar.
                //progressBar.setProgress(progress)
                println(progress)
            }

            if (isTerminateState(update.installState)) {
                //moduleInstallClient.unregisterListener(this)
            }
        }

        fun isTerminateState(@ModuleInstallStatusUpdate.InstallState state: Int): Boolean {
            return state == ModuleInstallStatusUpdate.InstallState.STATE_CANCELED || state == ModuleInstallStatusUpdate.InstallState.STATE_COMPLETED || state == ModuleInstallStatusUpdate.InstallState.STATE_FAILED
        }
    }

    val listener = ModuleInstallProgressListener()
}