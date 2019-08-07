package br.com.alura.agenda.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Aluno implements Parcelable {

    private final String nome;
    private final String telefone;
    private final String email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nome;
    }

    protected Aluno(Parcel in) {
        nome = in.readString();
        telefone = in.readString();
        email = in.readString();
    }

    public static final Creator<Aluno> CREATOR = new Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int index) {
        parcel.writeString(nome);
        parcel.writeString(telefone);
        parcel.writeString(email);
    }
}
