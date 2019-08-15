package br.com.alura.agenda.application;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    private final AlunoDAO dao = new AlunoDAO();

    @Override
    public void onCreate() {
        super.onCreate();
        inicializaAlunosDeTeste();
    }

    private void inicializaAlunosDeTeste() {
        dao.salvaAluno(new Aluno("Raul Fernandes", "998889328", "raulfelipe2@gmail.com"));
        dao.salvaAluno(new Aluno("Ana Maria", "996685328", "raulkfernandes@gmail.com"));
    }
}
