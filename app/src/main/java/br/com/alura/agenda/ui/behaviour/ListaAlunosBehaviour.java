package br.com.alura.agenda.ui.behaviour;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import br.com.alura.agenda.asynctask.BuscaListaDeAlunosTask;
import br.com.alura.agenda.asynctask.RemoveAlunoTask;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosBehaviour {

    private final Context mContext;
    private final ListaAlunosAdapter adapter;
    private final RoomAlunoDAO alunoDAO;

    public ListaAlunosBehaviour(Context mContext) {
        this.mContext = mContext;
        this.adapter = new ListaAlunosAdapter(mContext);
        this.alunoDAO = AgendaDatabase.getInstance(mContext).getRoomAlunoDAO();
    }

    public void confirmaRemoverAluno(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(mContext)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    removeAluno(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaListaDeAlunos() {
        new BuscaListaDeAlunosTask(alunoDAO, adapter).execute();
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }

    private void removeAluno(Aluno aluno) {
        new RemoveAlunoTask(alunoDAO, adapter, aluno).execute();
    }
}
