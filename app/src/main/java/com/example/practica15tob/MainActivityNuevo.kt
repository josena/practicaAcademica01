package com.example.practica15tob

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica15tob.databinding.ActivityMainBinding
import com.example.practica15tob.databinding.ActivityMainNuevoBinding

class MainActivityNuevo : AppCompatActivity() {

    //Vincular vistas con MainActivity
    private lateinit var binding: ActivityMainNuevoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNuevoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Click en el botón Guardar
        binding.btnGuardar.setOnClickListener {
//Pasamos los valores de los editText a variables
            val txtNom = binding.txtNombre.text
            val txtCue = binding.txtCuenta.text
            val txtCorr = binding.txtCorreo.text
            val txtImg = binding.txtImage.text

//Creamos el Intent para pasarnos al MainActivity y mandamos por extras los valores
            val intento2 = Intent(this, MainActivity::class.java)
            //usamos la etiqueta mensaje para indicar que es nuevo alumno
            intento2.putExtra("mensaje","nuevo")
            intento2.putExtra("nombre","${txtNom}")
            intento2.putExtra("cuenta","${txtCue}")
            intento2.putExtra("correo","${txtCorr}")
            intento2.putExtra("image","${txtImg}")
            startActivity(intento2)
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }

    }
}