package br.com.alura.financask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.extension.limitaEmAte
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val context: Context): BaseAdapter() {

    private val limiteDaCategoria = 14

    override fun getCount(): Int {
        return this.transacoes.size
    }

    override fun getItem(position: Int): Transacao {
        return this.transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada =
            LayoutInflater.from(this.context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        this.adicionaValor(transacao, viewCriada)
        this.adicionaIcone(transacao, viewCriada)
        this.adicionaCategoria(viewCriada, transacao)
        this.adicionaData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionaData(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun adicionaCategoria(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaIcone(
        transacao: Transacao,
        viewCriada: View
    ) {
        val icone = iconePor(transacao.tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo): Int {
        if (Tipo.RECEITA == tipo) {
            return R.drawable.icone_transacao_item_receita
        }
        return R.drawable.icone_transacao_item_despesa
    }

    private fun adicionaValor(
        transacao: Transacao,
        viewCriada: View
    ) {
       val cor: Int = corPor(transacao.tipo)
        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPor(tipo: Tipo): Int {
        if (Tipo.RECEITA == tipo) {
            return ContextCompat.getColor(this.context, R.color.receita)
        }
        return ContextCompat.getColor(this.context, R.color.despesa)
    }
}