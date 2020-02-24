package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface RoomTelefoneDAO {

//    @Query("SELECT t.* FROM " +
//            "Telefone t JOIN Aluno a " +
//            "ON t.alunoID = a.id " +
//            "WHERE t.alunoId = :alunoId " +
//            "LIMIT 1")
    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefone(int alunoId);
}
