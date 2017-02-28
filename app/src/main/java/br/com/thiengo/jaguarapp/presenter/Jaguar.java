package br.com.thiengo.jaguarapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;


public class Jaguar implements Parcelable {
    public static final String JAGUARS_KEY = "jaguars_key";

    private String modelo;
    private String motor;
    private String preco;
    private String urlImagem;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.modelo);
        dest.writeString(this.motor);
        dest.writeString(this.preco);
        dest.writeString(this.urlImagem);
    }

    public Jaguar() {}

    protected Jaguar(Parcel in) {
        this.modelo = in.readString();
        this.motor = in.readString();
        this.preco = in.readString();
        this.urlImagem = in.readString();
    }

    public static final Parcelable.Creator<Jaguar> CREATOR = new Parcelable.Creator<Jaguar>() {
        @Override
        public Jaguar createFromParcel(Parcel source) {
            return new Jaguar(source);
        }

        @Override
        public Jaguar[] newArray(int size) {
            return new Jaguar[size];
        }
    };
}
