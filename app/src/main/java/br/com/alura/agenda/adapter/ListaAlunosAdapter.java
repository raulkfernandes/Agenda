package br.com.alura.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context mContext;

    public ListaAlunosAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = LayoutInflater.from(mContext).inflate(R.layout.item_aluno, viewGroup, false);
        Aluno currentAluno = alunos.get(posicao);

        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(currentAluno.getNome());

        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(currentAluno.getTelefone());

        return viewCriada;
    }

    // Encapsulando métodos para que o adapter se encarregue dessas tarefas:
    public void clear() {
        alunos.clear();
        notificaAdapter();
    }

    public void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
        notificaAdapter();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notificaAdapter();
    }

    private void notificaAdapter() {
        this.notifyDataSetChanged();
    }
}
