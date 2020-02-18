package br.com.alura.agenda.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class Aluno implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String telefoneFixo;
    private String telefoneCelular;
    private String email;
    private Calendar momentoDeCadastro = Calendar.getInstance();

    public Aluno() {
    }

    public Calendar getMomentoDeCadastro() {
        return momentoDeCadastro;
    }

    public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
        this.momentoDeCadastro = momentoDeCadastro;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
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

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
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
        telefoneFixo = in.readString();
        telefoneCelular = in.readString();
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
        parcel.writeString(telefoneFixo);
        parcel.writeString(telefoneCelular);
        parcel.writeString(email);
        parcel.writeSerializable(momentoDeCadastro);
    }
}
