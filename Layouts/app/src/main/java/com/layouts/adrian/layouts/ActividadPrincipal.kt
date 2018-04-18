package com.layouts.adrian.layouts

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_actividad_principal.*
import android.widget.TextView
import android.support.v7.widget.DefaultItemAnimator
import android.widget.RelativeLayout
import android.widget.Toast


class ActividadPrincipal : AppCompatActivity() {
    var personas = ArrayList<Persona>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_principal)
        crearDatosPrueba()


        val layoutManager = LinearLayoutManager(this)
        personas.addAll(crearDatosPrueba())
        val adaptador = PersonasAdaptador(personas)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adaptador
        personas.addAll(crearDatosPrueba())
        adaptador.notifyDataSetChanged()
        Log.i("vista-principal", "Cambio personas ${personas.size}")
    }

    fun crearDatosPrueba(): ArrayList<Persona> {
        var personas = ArrayList<Persona>()
        personas.add(Persona("Vicente", "Eguez", "1718137159"))
        personas.add(Persona("Adrian", "Sarzosa", "1718138183"))
        personas.add(Persona("Wendy", "Eguez", "1718154323"))
        return personas
    }
}

class Persona(var nombre: String, var apellido: String, var cedula: String) {}

class PersonasAdaptador(private val listaPersonas: List<Persona>) : RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombre: TextView
        var apellido: TextView
        var cedula: TextView

        init {
            nombre = view.findViewById(R.id.txtv_nombre) as TextView
            apellido = view.findViewById(R.id.txtv_apellido) as TextView
            cedula = view.findViewById(R.id.txtv_cedula) as TextView
            val left = apellido.paddingLeft
            val top = apellido.paddingTop
            Log.i("vista-principal", "Hacia la izquierda es $left y hacia arriba es $top")

            val layout = view.findViewById(R.id.relative_layout) as RelativeLayout
            layout.setOnClickListener({ v ->
                val nombreActual = v.findViewById(R.id.txtv_nombre) as TextView

                Log.i("vista-principal", "El nombre actual es: ${nombreActual.text}")
                nombreActual.text = "Otro texto"

            })
            cedula.setOnClickListener { v ->
                val cedulaActual = v.findViewById(R.id.txtv_cedula) as TextView
                val toast = Toast.makeText(v.context, "Hola ${cedulaActual.text}", Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(v.context, ActividadLayouts::class.java)
                startActivity(v.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lista_fila_usuario, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val persona = listaPersonas[position]
        holder.nombre.setText(persona.nombre)
        holder.apellido.setText(persona.apellido)
        holder.cedula.setText(persona.cedula)
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }

}
