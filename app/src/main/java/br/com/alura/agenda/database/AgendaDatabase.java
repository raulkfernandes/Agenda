package br.com.alura.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.database.converter.ConversorTipoTelefone;
import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

import static br.com.alura.agenda.database.AgendaMigrations.ALL_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})

public abstract class AgendaDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "agenda.db";

    private static AgendaDatabase instance;

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public abstract RoomTelefoneDAO getRoomTelefoneDAO();

    public static AgendaDatabase getInstance(Context mContext) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(mContext, AgendaDatabase.class, DATABASE_NAME)
                    //.allowMainThreadQueries()
                    //.fallbackToDestructiveMigration()
                    .addMigrations(ALL_MIGRATIONS)
                    .build();
        }
        return instance;
    }
}