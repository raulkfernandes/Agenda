package br.com.alura.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class})

public abstract class AgendaDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "agenda.db";
    private static AgendaDatabase instance;

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context mContext) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(mContext, AgendaDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    //.fallbackToDestructiveMigration()
                    .addMigrations(
                            new Migration(1, 2) {
                                @Override
                                public void migrate(@NonNull SupportSQLiteDatabase database) {
                                    database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
                                }
                            },
                            new Migration(2, 3) {
                                @Override
                                public void migrate(@NonNull SupportSQLiteDatabase database) {
                                    // Criar nova tabela com as informações desejadas
                                    database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                            "`nome` TEXT," +
                                            "`telefone` TEXT," +
                                            "`email` TEXT)");

                                    // Copiar dados da tabela antiga para a tabela nova
                                    database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email) " +
                                            "SELECT id, nome, telefone, email FROM Aluno");

                                    // Remover tabela antiga
                                    database.execSQL("DROP TABLE Aluno");

                                    // Renomear a tabela nova com o mesmo nome da tabela antiga
                                    database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
                                }
                            },
                            new Migration(3, 4) {
                                @Override
                                public void migrate(@NonNull SupportSQLiteDatabase database) {
                                    database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
                                }
                            })
                    .build();
        }
        return instance;
    }
}