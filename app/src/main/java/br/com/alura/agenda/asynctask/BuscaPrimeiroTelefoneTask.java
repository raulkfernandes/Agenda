package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneTask extends AsyncTask<Void, Void, Telefone> {
    private final RoomTelefoneDAO telefoneDAO;
    private final int alunoId;
    private final TelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneTask(RoomTelefoneDAO telefoneDAO, int alunoId, TelefoneEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDAO.buscaPrimeiroTelefone(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);
    }

    public interface TelefoneEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
