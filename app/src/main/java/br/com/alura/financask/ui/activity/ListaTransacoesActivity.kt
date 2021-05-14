package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = this.transacoesDeExemplo()

        this.configuraLista(transacoes)
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
            tipo = Tipo.RECEITA,
            categoria = "Prêmio"
        )
    )
}