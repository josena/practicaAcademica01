package com.example.practica15tob

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica15tob.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var data = ArrayList<Alumno>()

    private lateinit var rvAdapter: AlumnoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerview = binding.rvPersonas
        recyclerview.layoutManager = LinearLayoutManager(this)

        // Inicializamos la lista solo una vez
        if (!::rvAdapter.isInitialized) {
            data.add(
                Alumno(
                    nombre = "José Antonio Ramírez",
                    "20105541",
                    "jmorfin@ucol.mx",
                    "https://www.nintendo.com/eu/media/images/08_content_images/news_5/2016_1/august_10/CI7_PokemonFeatureNews_Pikachu_image912w.jpg"
                )
            )

            rvAdapter = AlumnoAdapter(this, data)
            recyclerview.adapter = rvAdapter

            rvAdapter.setOnItemClickListener(object : AlumnoAdapter.ClickListener {
                override fun onItemClick(view: View, position: Int) {
                    itemOptiomsMenu(position)
                }
            })
        }

        // Recibir extras para agregar alumno nuevo
        val parExtra = intent.extras
        val msje = parExtra?.getString("mensaje")
        val nombre = parExtra?.getString("nombre")
        val cuenta = parExtra?.getString("cuenta")
        val correo = parExtra?.getString("correo")
        val image = parExtra?.getString("image")

        if (msje == "nuevo" && nombre != null && cuenta != null && correo != null && image != null) {
            val insertIndex: Int = data.count()
            data.add(
                insertIndex,
                Alumno(
                    nombre,
                    cuenta,
                    correo,
                    image
                )
            )
            rvAdapter.notifyItemInserted(insertIndex)
        }

        // Botón para agregar un nuevo alumno
        binding.faButton.setOnClickListener {
            val intento1 = Intent(this, MainActivityNuevo::class.java)
            startActivity(intento1)
        }
    }

    private fun itemOptiomsMenu(position: Int) {
        val popupMenu = PopupMenu(this@MainActivity,binding.rvPersonas[position].findViewById(R.id.textViewOptions))
        popupMenu.inflate(R.menu.options_menu)
//Para cambiarnos de activity
        val intento2 = Intent(this, MainActivityNuevo::class.java)
//Implementar el click en el item
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.borrar -> {
                        val tmpAlum = data[position]
                        data.remove(tmpAlum)
                        rvAdapter.notifyDataSetChanged()
                        return true
                    }
                    R.id.editar ->{
                        //Tomamos los datos del alumno, en la posición de la lista donde hicieron click
                        val nombre = data[position].nombre
                        val cuenta = data[position].cuenta
                        val correo = data[position].correo
                        val image = data[position].imagen
                        //En position tengo el indice del elemento en la lista
                        val idAlum: Int = position
                        intento2.putExtra("mensaje","edit")
                        intento2.putExtra("nombre","${nombre}")
                        intento2.putExtra("cuenta","${cuenta}")
                        intento2.putExtra("correo","${correo}")
                        intento2.putExtra("image","${image}")
                        //Pasamos por extras el idAlum para poder saber cual editar de la lista (ArrayList)
                        intento2.putExtra("idA",idAlum)
                        startActivity(intento2)
                        return true
                    }
                }
                return false
            }
        })
        popupMenu.show()
    }
}