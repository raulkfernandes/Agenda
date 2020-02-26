package br.com.alura.agenda.asynctask;

import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class SalvaAlunoTask extends BaseFinalizaAlunoTask {
    private final RoomAlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final RoomTelefoneDAO telefoneDAO;

    public SalvaAlunoTask(RoomAlunoDAO alunoDAO, Aluno aluno,
                          Telefone telefoneFixo, Telefone telefoneCelular,
                          RoomTelefoneDAO telefoneDAO, TaskFinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDAO.salvaAluno(aluno).intValue();
        vinculaAlunoTelefones(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salvaTelefone(telefoneFixo, telefoneCelular);
        return null;
    }
}
