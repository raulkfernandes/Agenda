package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.ui.adapter.ListaAlunosViewHolder;

public class BuscaPrimeiroTelefoneTask extends AsyncTask<Void, Void, Telefone> {
    private final RoomTelefoneDAO telefoneDAO;
    private final ListaAlunosViewHolder viewHolder;
    private final int alunoId;

    public BuscaPrimeiroTelefoneTask(RoomTelefoneDAO telefoneDAO, ListaAlunosViewHolder viewHolder, int alunoId) {
        this.telefoneDAO = telefoneDAO;
        this.viewHolder = viewHolder;
        this.alunoId = alunoId;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDAO.buscaPrimeiroTelefone(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone telefone) {
        super.onPostExecute(telefone);
        if (telefone != null) {
            viewHolder.setTextTelefone(telefone.getNumero());
        }
    }
}
