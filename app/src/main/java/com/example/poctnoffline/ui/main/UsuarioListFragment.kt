package com.example.poctnoffline.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poctnoffline.R
import com.example.poctnoffline.UsuarioViewModel
import com.example.poctnoffline.adapter.UsuarioListAdapter

class UsuarioListFragment : Fragment() {

    private var listener: OnListFragmentInteractionListener =
        object : OnListFragmentInteractionListener {
            override fun onListFragmentInteraction(item: Int){}
        }

    private lateinit var usuarioAdapter: UsuarioListAdapter
    private lateinit var viewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Set the adapter
        this.usuarioAdapter =
            UsuarioListAdapter(
                listener
            )

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usuarioAdapter
        }

        return view
    }


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.usuarios.observe(this, Observer {
            if (it != null) {
                usuarioAdapter.values = it
                usuarioAdapter.notifyDataSetChanged()
            }
        })
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Int)
    }

}


