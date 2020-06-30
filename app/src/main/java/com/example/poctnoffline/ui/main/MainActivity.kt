package com.example.poctnoffline.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poctnoffline.R
import com.example.poctnoffline.UsuarioViewModel
import com.example.poctnoffline.adapter.UsuarioListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    private var listener: UsuarioListFragment.OnListFragmentInteractionListener =
        object : UsuarioListFragment.OnListFragmentInteractionListener {
            override fun onListFragmentInteraction(item: Int) {
                val intent = Intent(this@MainActivity, DetalheActivity::class.java)
                intent.putExtra("item", item)
                startActivity(intent)
            }
        }

    private val viewModel : UsuarioViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        val adapter = UsuarioListAdapter(listener)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        viewModel.usuarios.observe(this, Observer {
            adapter.values = it
            adapter.notifyDataSetChanged()
        })

        fab_sync.setOnClickListener {
            viewModel.loadRemoteUsuarios()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.reloadLocal()
    }

}



