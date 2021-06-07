package br.com.alura.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.ui.ResumoView
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = this.transacoesDeExemplo()

        this.configuraResumo(transacoes)

        this.configuraLista(transacoes)

        lista_transacoes_adiciona_receita.setOnClickListener {
            val view: View = window.decorView
            val viewCriada = LayoutInflater.from(this)
                .inflate(
                    R.layout.form_transacao,
                    view as ViewGroup,
                    false
                )

            val ano = 2017
            val mes = 9
            val dia = 18

            val hoje = Calendar.getInstance()
            viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())

            viewCriada.form_transacao_data.setOnClickListener {
                DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        viewCriada.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                    }, ano, mes, dia
                ).show()
            }

            AlertDialog.Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setView(view)
                .show()
        }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view: View = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo() = listOf(
        Transacao(
            valor = BigDecimal(20.5),
            tipo = Tipo.DESPESA,
            categoria = "Almoço de Final de Semana"
        ),
        Transacao(
            valor = BigDecimal(100),
            categoria = "Economia",
            tipo = Tipo.RECEITA
        ),
        Transacao(
            valor = BigDecimal(200),
            tipo = Tipo.DESPESA
        ),
        Transacao(
            valor = BigDecimal(500),
            tipo = Tipo.DESPESA,
            categoria = "Prêmio"
        )
    )
}