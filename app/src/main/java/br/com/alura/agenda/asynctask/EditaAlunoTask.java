package br.com.alura.agenda.asynctask;

import java.util.List;

import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

public class EditaAlunoTask extends BaseFinalizaAlunoTask {
    private final RoomAlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final RoomTelefoneDAO telefoneDAO;
    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(RoomAlunoDAO alunoDAO, Aluno aluno,
                          Telefone telefoneFixo, Telefone telefoneCelular,
                          RoomTelefoneDAO telefoneDAO, List<Telefone> telefonesDoAluno,
                          TaskFinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.editaAluno(aluno);
        vinculaAlunoTelefones(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaTelefoneIDs(telefoneFixo, telefoneCelular);
        telefoneDAO.editaTelefone(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizaTelefoneIDs(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }
}
