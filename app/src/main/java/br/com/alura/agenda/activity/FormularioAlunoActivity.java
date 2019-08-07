package br.com.alura.agenda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        final AlunoDAO dao = new AlunoDAO();

        final EditText campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        final EditText campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        final EditText campoEmail = findViewById(R.id.activity_formulario_aluno_email);

        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmail.getText().toString();

                if(nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                    Toast.makeText(FormularioAlunoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Aluno alunoCriado = new Aluno(nome, telefone, email);
                    dao.setAluno(alunoCriado);
                    Toast.makeText(FormularioAlunoActivity.this, "Aluno Salvo!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
