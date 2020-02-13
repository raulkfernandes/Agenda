package br.com.alura.agenda.application;

import android.app.Application;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //inicializaAlunosDeTeste();
    }

//    private void inicializaAlunosDeTeste() {
//        AgendaDatabase database = Room
//                .databaseBuilder(this, AgendaDatabase.class, "agenda.db")
//                .allowMainThreadQueries()
//                .build();
//        RoomAlunoDAO dao = database.getRoomAlunoDAO();
//
//        dao.salvaAluno(new Aluno("Raul Fernandes", "998889328", "raulfelipe2@gmail.com"));
//        dao.salvaAluno(new Aluno("Ana Maria", "996685328", "raulkfernandes@gmail.com"));
//    }
}
