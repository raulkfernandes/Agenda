package br.com.alura.agenda.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.adapter.ListaAlunosAdapter;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.activity.ConstantesEntreActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraListaDeAlunos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaDeAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        configuraContextMenu(item);
        return super.onContextItemSelected(item);
    }

    private void configuraContextMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_alunos_menu_remover) {
            confirmaRemoverAluno(item);
        }
    }

    private void confirmaRemoverAluno(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(this)
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

    private void atualizaListaDeAlunos() {
        adapter.atualiza(dao.getListaDeAlunos());
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton fabNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        fabNovoAluno.setOnClickListener(view -> abreFormularioAlunoActivity());
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    private void configuraListaDeAlunos() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaDeAlunos);
        configuraCliqueNaLista(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }

    private void configuraCliqueNaLista(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, index, id) -> {
            Aluno alunoSelecionado = (Aluno) adapterView.getItemAtPosition(index);
            editaFormularioAlunoActivity(alunoSelecionado);
        });
    }

    private void editaFormularioAlunoActivity(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void removeAluno(Aluno aluno) {
        dao.removeAluno(aluno);
        adapter.remove(aluno);
    }
}
