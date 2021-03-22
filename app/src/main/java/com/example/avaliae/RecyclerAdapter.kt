package com.example.avaliae

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliae.model.Empresa
import kotlinx.android.synthetic.main.recycler_list_restaurantes.view.*

class RecyclerAdapter (
    private val empresas: List<Empresa>,
    val actionClick: (Empresa) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.EmpresasViewHolder>() {

    class EmpresasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNome: TextView = itemView.textViewRVNome
        val textNota: TextView = itemView.textViewRVNota
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresasViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_list_restaurantes,
                parent,
                false
            )
        return EmpresasViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmpresasViewHolder, position: Int) {
        val empresa = empresas[position]
        holder.textNome.text = empresa.nome
        holder.textNota.text = empresa.nota.toString()


        holder.itemView.setOnClickListener {
            actionClick(empresa)
        }

    }

    override fun getItemCount(): Int = empresas.size
}