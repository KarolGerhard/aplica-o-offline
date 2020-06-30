package com.example.poctnoffline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.poctnoffline.R
import com.example.poctnoffline.model.Usuario
import com.example.poctnoffline.ui.main.UsuarioListFragment
import kotlinx.android.synthetic.main.item_card.view.*
import java.util.ArrayList


class UsuarioListAdapter (
    private val listener: UsuarioListFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var values: List<Usuario> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Int
            listener?.onListFragmentInteraction(item = item)
        }
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        else -> ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_card, parent, false)
                ItemViewHolder(view)
            }
            else -> throw Error()
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val item = values[position]
                holder.id.text = item.id.toString()
                holder.cpf.text = item.cpf
                holder.nome.text = item.nome
                holder.versao.text = item.rowVersion.toString()
                holder.ultimaAlteracao.text = item.ultimaAlteracao.toString()
//                if (item.status){
//                    holder.statusSync.setImageResource(
//                        R.drawable.ic_atualizado
//                    )
//                }else{
//                    holder.statusSync.setImageResource(
//                        R.drawable.ic_atualizar
//                    )
               // }
//                when (item.status.toString()) {
//                    true -> holder.statusSync.setImageResource(
//                        R.drawable.ic_atualizado
//                    )
//                    "Atualizar" -> {
//                        holder.statusSync.setImageResource(
//                            R.drawable.ic_atualizar
//                        )
//                    }
//                    "Offline" -> {
//                        holder.statusSync.setImageResource(
//                            R.drawable.ic_offline
//                        )
//                    }
//                    else -> {
//
//                    }
//                }

                with(holder.view) {
                    tag = item.id
                    setOnClickListener(onClickListener)
                }
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ItemViewHolder(val view: View) : ViewHolder(view) {
        val id: TextView = view.nroIDLabel
        val cpf: TextView = view.cpfNroLabel
        val nome: TextView = view.tv_nomeLabel
        val versao: TextView = view.tv_versaoLabel
        val ultimaAlteracao: TextView = view.tv_dataLabel
    }

    companion object {
        private const val ITEM = 0
    }
}



