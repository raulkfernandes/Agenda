package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.database.dao.RoomTelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

import static br.com.alura.agenda.ui.activity.ConstantesEntreActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private RoomAlunoDAO alunoDAO;
    private RoomTelefoneDAO telefoneDAO;
    private Aluno aluno;
    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        alunoDAO = AgendaDatabase.getInstance(this).getRoomAlunoDAO();
        telefoneDAO = AgendaDatabase.getInstance(this).getRoomTelefoneDAO();
        inicializaCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        configuraOptionsMenu(item);
        return super.onOptionsItemSelected(item);
    }

    private void configuraOptionsMenu(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_formulario_aluno_menu_salvar) {
            finalizaFormulario();
        }
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = dados.getParcelableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        telefonesDoAluno = telefoneDAO.buscaTodosTelefones(aluno.getId());
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                campoTelefoneFixo.setText(telefone.getNumero());
            } else {
                campoTelefoneCelular.setText(telefone.getNumero());
            }
        }
        campoEmail.setText(aluno.getEmail());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (checaCamposVazios()) {
            Toast.makeText(FormularioAlunoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        } else if (aluno.hasValidId()) {
            Toast.makeText(FormularioAlunoActivity.this, "Informações atualizadas!", Toast.LENGTH_SHORT).show();
            alunoDAO.editaAluno(aluno);

            String numeroFixo = campoTelefoneFixo.getText().toString();
            Telefone telefoneFixo = new Telefone(numeroFixo, TipoTelefone.FIXO, aluno.getId());
            String numeroCelular = campoTelefoneCelular.getText().toString();
            Telefone telefoneCelular = new Telefone(numeroCelular, TipoTelefone.CELULAR, aluno.getId());
            for (Telefone telefone : telefonesDoAluno) {
                if (telefone.getTipo() == TipoTelefone.FIXO) {
                    telefone.setId(telefoneFixo.getId());
                } else {
                    telefone.setId(telefoneCelular.getId());
                }
            }
            telefoneDAO.editaTelefone(telefoneFixo, telefoneCelular);

            finish();
        } else {
            Toast.makeText(FormularioAlunoActivity.this, "Informações salvas!", Toast.LENGTH_SHORT).show();
            int alunoId = alunoDAO.salvaAluno(aluno).intValue();
            String numeroFixo = campoTelefoneFixo.getText().toString();
            Telefone telefoneFixo = new Telefone(numeroFixo, TipoTelefone.FIXO, alunoId);
            String numeroCelular = campoTelefoneCelular.getText().toString();
            Telefone telefoneCelular = new Telefone(numeroCelular, TipoTelefone.CELULAR, alunoId);
            telefoneDAO.salvaTelefone(telefoneFixo, telefoneCelular);

            finish();
        }
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
    }

    private boolean checaCamposVazios() {
        return campoNome.getText().toString().isEmpty()
                || campoTelefoneFixo.getText().toString().isEmpty()
                || campoTelefoneCelular.getText().toString().isEmpty()
                || campoEmail.getText().toString().isEmpty();
    }
}
