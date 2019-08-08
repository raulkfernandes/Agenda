package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int idCount = 1;

    public void saveAluno(Aluno aluno) {
        aluno.setId(idCount);
        alunos.add(aluno);
        atualizaId();
    }

    private void atualizaId() {
        idCount++;
    }

    public void editAluno(Aluno alunoSelecionado) {
        Aluno aluno = checkAlunoById(alunoSelecionado);
        if(aluno != null) {
            int posicaoAluno = alunos.indexOf(aluno);
            alunos.set(posicaoAluno, alunoSelecionado);
        }
    }

    private Aluno checkAlunoById(Aluno alunoSelecionado) {
        for (Aluno aluno : alunos) {
            if(aluno.getId() == alunoSelecionado.getId()) {
                return aluno;
            }
        }
        return null;
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
}
