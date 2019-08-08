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
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);

        inicializaCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        if(dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getParcelableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        }
        else {
            aluno = new Aluno();
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
                preencheAluno();
                if(checaCamposVazios()) {
                    Toast.makeText(FormularioAlunoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
                else if(aluno.hasValidId()) {
                    Toast.makeText(FormularioAlunoActivity.this, "Informações atualizadas!", Toast.LENGTH_SHORT).show();
                    dao.editAluno(aluno);
                    finish();
                }
                else {
                    Toast.makeText(FormularioAlunoActivity.this, "Informações salvas!", Toast.LENGTH_SHORT).show();
                    dao.saveAluno(aluno);
                    finish();
                }
            }
        });
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

    private boolean checaCamposVazios() {
        if(campoNome.getText().toString().isEmpty() || campoNome.getText().toString().isEmpty() || campoNome.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }
}
