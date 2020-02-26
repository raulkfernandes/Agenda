package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class SalvaAlunoTask extends AsyncTask<Void, Void, Void> {
    private final RoomAlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final RoomTelefoneDAO telefoneDAO;
    private final QuandoAlunoSalvoLisneter listener;

    public SalvaAlunoTask(RoomAlunoDAO alunoDAO, Aluno aluno, Telefone telefoneFixo, Telefone telefoneCelular, RoomTelefoneDAO telefoneDAO, QuandoAlunoSalvoLisneter listener) {
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDAO.salvaAluno(aluno).intValue();
        vinculaAlunoTelefones(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salvaTelefone(telefoneFixo, telefoneCelular);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoAlunoSalvo();
    }

    private void vinculaAlunoTelefones(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    public interface QuandoAlunoSalvoLisneter {
        void quandoAlunoSalvo();
    }
}
