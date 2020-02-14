package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.behaviour.ListaAlunosBehaviour;

import static br.com.alura.agenda.ui.activity.ConstantesEntreActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de alunos";
    private ListaAlunosBehaviour listaAlunosBehaviour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);

        listaAlunosBehaviour = new ListaAlunosBehaviour(this);
        configuraFabNovoAluno();
        configuraListaDeAlunos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosBehaviour.atualizaListaDeAlunos();
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
            listaAlunosBehaviour.confirmaRemoverAluno(item);
        }
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton fabNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        fabNovoAluno.setOnClickListener(view -> insereFormularioAlunoActivity());
    }

    private void insereFormularioAlunoActivity() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    private void configuraListaDeAlunos() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunosBehaviour.configuraAdapter(listaDeAlunos);
        configuraCliqueNaLista(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
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
}