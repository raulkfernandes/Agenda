package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int idCount = 1;

    private void atualizaId() {
        idCount++;
    }

    private Aluno checaAlunoPeloId(Aluno alunoSelecionado) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == alunoSelecionado.getId()) {
                return aluno;
            }
        }
        return null;
    }

    public void salvaAluno(Aluno aluno) {
        aluno.setId(idCount);
        alunos.add(aluno);
        atualizaId();
    }

    public void removeAluno(Aluno alunoSelecionado) {
        Aluno aluno = checaAlunoPeloId(alunoSelecionado);
        if (aluno != null) {
            alunos.remove(aluno);
        }
    }

    public void editaAluno(Aluno alunoSelecionado) {
        Aluno aluno = checaAlunoPeloId(alunoSelecionado);
        if (aluno != null) {
            int posicaoAluno = alunos.indexOf(aluno);
            alunos.set(posicaoAluno, alunoSelecionado);
        }
    }

    public List<Aluno> getListaDeAlunos() {
        return new ArrayList<>(alunos);
    }
}
