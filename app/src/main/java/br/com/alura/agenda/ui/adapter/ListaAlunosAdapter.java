package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.asynctask.BuscaPrimeiroTelefoneTask;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context mContext;
    private final RoomTelefoneDAO telefoneDAO;

    public ListaAlunosAdapter(Context context) {
        this.mContext = context;
        this.telefoneDAO = AgendaDatabase.getInstance(context).getRoomTelefoneDAO();
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
    public View getView(int posicao, View convertView, ViewGroup parentView) {
        View novaView;
        ListaAlunosViewHolder viewHolder;
        if (convertView == null) {
            novaView = criaView(parentView);
            viewHolder = new ListaAlunosViewHolder(novaView);
            novaView.setTag(viewHolder);
        } else {
            novaView = convertView;
            viewHolder = (ListaAlunosViewHolder) novaView.getTag();
        }
        Aluno novoAluno = this.alunos.get(posicao);
        vincula(viewHolder, novoAluno);
        return novaView;
    }

    private View criaView(ViewGroup parentView) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_aluno, parentView, false);
    }

    private void vincula(ListaAlunosViewHolder viewHolder, Aluno aluno) {
        viewHolder.setTextNome(aluno.getNome());
        new BuscaPrimeiroTelefoneTask(telefoneDAO, aluno.getId(), telefoneEncontrado ->
                viewHolder.setTextTelefone(telefoneEncontrado.getNumero())).execute();
    }

    // Encapsulando métodos para que o adapter se encarregue dessas tarefas:
    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notificaAdapter();
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notificaAdapter();
    }

    private void notificaAdapter() {
        this.notifyDataSetChanged();
    }
}
