package br.com.alura.agenda.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Aluno implements Parcelable {

    private int id = 0;
    private String nome;
    private String telefone;
    private String email;

    // Constructors
    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() { }

    // Getters
    public int getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(int id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public void setEmail(String email) { this.email = email; }

    // Model Methods
    public boolean hasValidId() {
        return id > 0;
    }

    @Override
    public String toString() {
        return nome;
    }

    // Implementing Parcelable interface
    private Aluno(Parcel in) {
        id = in.readInt();
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
        parcel.writeInt(id);
        parcel.writeString(nome);
        parcel.writeString(telefone);
        parcel.writeString(email);
    }
}
