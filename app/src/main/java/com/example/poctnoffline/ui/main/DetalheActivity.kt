package com.example.poctnoffline.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.poctnoffline.DetalheViewModel
import com.example.poctnoffline.R
import kotlinx.android.synthetic.main.fragment_detalhe_usuario.*
import kotlinx.android.synthetic.main.item_card.cpfNroLabel
import kotlinx.android.synthetic.main.item_card.nroIDLabel

class DetalheActivity : AppCompatActivity() {

    private val viewModel: DetalheViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detalhe_usuario)


        var itemId = intent.getIntExtra("item", 0)


        viewModel.usuarioId.observe(this, Observer {
            nroIDLabel.text = it.toString()
        })

        viewModel.cpf.observe(this, Observer {
            cpfNroLabel.text = it.toString()
        })

        viewModel.nome.observe(this, Observer {
            nomeEditText.setText(it)
        })

        viewModel.rowVersion.observe(this, Observer {
            btn_enviar.isEnabled = it == 0L
            tvStatus.text = if (it == 0L ) "Local" else "Remoto"
        })

        viewModel.getDetalhe(itemId)

        btn_enviar.setOnClickListener {
            var nomeAtual = nomeEditText.text.toString()
            viewModel.setNome(nomeAtual)
            viewModel.enviarServidor()
        }

        btn_salvar.setOnClickListener {
            var nomeAtual = nomeEditText.text.toString()
            viewModel.setNome(nomeAtual)
             viewModel.salvar()
        }
    }

}




