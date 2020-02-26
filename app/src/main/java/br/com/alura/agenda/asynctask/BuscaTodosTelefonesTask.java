package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class BuscaTodosTelefonesTask extends AsyncTask<Void, Void, List<Telefone>> {
    private final RoomTelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TodosTelefonesEncontradosListener listener;

    public BuscaTodosTelefonesTask(RoomTelefoneDAO telefoneDAO, Aluno aluno, TodosTelefonesEncontradosListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefones(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);

    }

    public interface TodosTelefonesEncontradosListener {
        void quandoEncontrados(List<Telefone> telefones);
    }
}
