package br.com.alura.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "agenda.db";
    private static AgendaDatabase instance;

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context mContext) {
        if(instance == null) {
            instance = Room
                    .databaseBuilder(mContext, AgendaDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
            return instance;
    }
}
