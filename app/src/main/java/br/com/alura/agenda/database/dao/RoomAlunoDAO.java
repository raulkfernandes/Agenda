package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Aluno;

@Dao
public interface RoomAlunoDAO {
    @Insert
    Long salvaAluno(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> getListaDeAlunos();

    @Delete
    void removeAluno(Aluno aluno);

    @Update
    void editaAluno(Aluno aluno);
}
