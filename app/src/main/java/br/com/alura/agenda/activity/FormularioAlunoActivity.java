package br.com.alura.agenda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);

        inicializaCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        Aluno alunoSelecionado = (Aluno) dados.getParcelableExtra("aluno");
        if(alunoSelecionado != null) {
            campoNome.setText(alunoSelecionado.getNome());
            campoTelefone.setText(alunoSelecionado.getTelefone());
            campoEmail.setText(alunoSelecionado.getEmail());
        }
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno alunoCriado = criaAluno();
                if(alunoCriado != null) {
                    salvaAluno(alunoCriado);
                }
            }
        });
    }

    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        if(nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Toast.makeText(FormularioAlunoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return null;
        }

        return new Aluno(nome, telefone, email);
    }

    private void salvaAluno(Aluno alunoCriado) {
        dao.setAluno(alunoCriado);
        Toast.makeText(FormularioAlunoActivity.this, "Aluno salvo!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
