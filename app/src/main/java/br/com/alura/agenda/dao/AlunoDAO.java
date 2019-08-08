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
        idCount++;
    }

    public Aluno findAluno(int i) {
        return alunos.get(i);
    }

    public void editAluno(Aluno aluno) {
        Aluno alunoSelecionado = null;
        for (Aluno a : alunos) {
            if(a.getId() == aluno.getId()) {
                alunoSelecionado = a;
            }
        }
        if(alunoSelecionado != null) {
            int posicaoAluno = alunos.indexOf(alunoSelecionado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
}
