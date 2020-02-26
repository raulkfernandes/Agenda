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
import br.com.alura.agenda.asynctask.BuscaTodosTelefonesTask;
import br.com.alura.agenda.asynctask.EditaAlunoTask;
import br.com.alura.agenda.asynctask.SalvaAlunoTask;
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
        campoEmail.setText(aluno.getEmail());
        preencheCamposTelefone();
    }

    private void preencheCamposTelefone() {
        new BuscaTodosTelefonesTask(telefoneDAO, aluno, telefones -> {
            telefonesDoAluno = telefones;
            for (Telefone telefone : telefonesDoAluno) {
                if (telefone.getTipo() == TipoTelefone.FIXO) {
                    campoTelefoneFixo.setText(telefone.getNumero());
                } else {
                    campoTelefoneCelular.setText(telefone.getNumero());
                }
            }
        }).execute();
    }

    private void finalizaFormulario() {
        preencheAluno();
        Telefone telefoneFixo = criaTelefone(campoTelefoneFixo, TipoTelefone.FIXO);
        Telefone telefoneCelular = criaTelefone(campoTelefoneCelular, TipoTelefone.CELULAR);
        if (aluno.hasValidId()) {
            Toast.makeText(FormularioAlunoActivity.this, "Informações atualizadas!", Toast.LENGTH_SHORT).show();
            editaAluno(telefoneFixo, telefoneCelular);
        } else {
            Toast.makeText(FormularioAlunoActivity.this, "Informações salvas!", Toast.LENGTH_SHORT).show();
            salvaAluno(telefoneFixo, telefoneCelular);
        }
    }

    private void editaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new EditaAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO, telefonesDoAluno, this::finish).execute();
    }

    private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new SalvaAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO, this::finish).execute();
    }

    private Telefone criaTelefone(EditText campoTelefone, TipoTelefone tipo) {
        String numero = campoTelefone.getText().toString();
        return new Telefone(numero, tipo);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
    }
}
