package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public int sizeAlunos() {
        return alunos.size();
    }

    public void setAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public Aluno findAluno(int i) {
        return alunos.get(i);
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
}
