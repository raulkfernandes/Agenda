package br.com.alura.agenda.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.List;

@Entity
public class Aluno implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String email;
    private Calendar momentoDeCadastro = Calendar.getInstance();
    private List<Telefone> telefones;

    public Aluno() {
    }

    public Calendar getMomentoDeCadastro() {
        return momentoDeCadastro;
    }

    public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
        this.momentoDeCadastro = momentoDeCadastro;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean hasValidId() {
        return id > 0;
    }

//    public String getDataDeCadastro() {
//        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        return formatador.format(momentoDeCadastro.getTime());
//    }

    // Implementing Parcelable interface
    private Aluno(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        email = in.readString();
        momentoDeCadastro = (Calendar) in.readSerializable();
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
        parcel.writeString(email);
        parcel.writeSerializable(momentoDeCadastro);
    }
}
