package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Telefone;

abstract class BaseFinalizaAlunoTask extends AsyncTask<Void, Void, Void> {

    private final TaskFinalizadaListener listener;

    BaseFinalizaAlunoTask(TaskFinalizadaListener listener) {
        this.listener = listener;
    }

    void vinculaAlunoTelefones(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    public interface TaskFinalizadaListener {
        void quandoFinalizada();
    }
}
