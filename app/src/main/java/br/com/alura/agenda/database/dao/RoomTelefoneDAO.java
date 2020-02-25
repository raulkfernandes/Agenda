package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

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

    @Insert
    void salvaTelefone(Telefone... telefones);

    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId")
    List<Telefone> buscaTodosTelefones(int alunoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void editaTelefone(Telefone... telefones);
}
