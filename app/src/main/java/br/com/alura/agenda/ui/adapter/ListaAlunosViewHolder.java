package br.com.alura.agenda.ui.adapter;

import android.view.View;
import android.widget.TextView;

import br.com.alura.agenda.R;

class ListaAlunosViewHolder {
    private final TextView nome;
    private final TextView telefone;

    ListaAlunosViewHolder(View view) {
        this.nome = view.findViewById(R.id.item_aluno_nome);
        this.telefone = view.findViewById(R.id.item_aluno_telefone);
    }

    void setTextNome(String nomeAluno) {
        this.nome.setText(nomeAluno);
    }

    void setTextTelefone(String telefoneAluno) {
        this.telefone.setText(telefoneAluno);
    }
}
