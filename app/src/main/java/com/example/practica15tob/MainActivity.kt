package com.example.practica15tob

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica15tob.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recycleVPersonas = binding.rvPersonas
        recycleVPersonas.layoutManager = LinearLayoutManager(this)

        var data = ArrayList<Alumno>()

        for (i in 1 .. 50){
            data.add(Alumno(nombre = "José Nabor Ramírez","20105541","jmorfin@ucol.mx","https://cdn.pixabay.com/photo/2021/12/26/17/31/pokemon-6895600_640.png"))
        }

        val adapter = AlumnoAdapter(this, data)
        recycleVPersonas.adapter = adapter
    }
}