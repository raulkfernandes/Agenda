package br.com.alura.agenda.ui.behaviour;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosBehaviour {

    private final Context mContext;
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;

    public ListaAlunosBehaviour(Context mContext) {
        this.mContext = mContext;
        this.adapter = new ListaAlunosAdapter(this.mContext);
        this.dao = new AlunoDAO();
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
        adapter.atualiza(dao.getListaDeAlunos());
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }

    private void removeAluno(Aluno aluno) {
        dao.removeAluno(aluno);
        adapter.remove(aluno);
    }
}
