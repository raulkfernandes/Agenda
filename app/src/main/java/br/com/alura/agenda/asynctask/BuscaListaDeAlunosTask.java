package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class BuscaListaDeAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final ListaAlunosAdapter adapter;
    private final RoomAlunoDAO dao;

    public BuscaListaDeAlunosTask(ListaAlunosAdapter adapter, RoomAlunoDAO dao) {
        this.adapter = adapter;
        this.dao = dao;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.getListaDeAlunos();
    }

    @Override
    protected void onPostExecute(List<Aluno> listaDeAlunos) {
        super.onPostExecute(listaDeAlunos);
        adapter.atualiza(listaDeAlunos);
    }
}
