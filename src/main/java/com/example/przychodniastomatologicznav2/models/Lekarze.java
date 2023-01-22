package com.example.przychodniastomatologicznav2.models;

public class Lekarze {
    private int id_lekarza;
    private String imie_lekarza, nazwisko_lekarza, numer_telefonu;

    public Lekarze(int id_lekarza, String imie, String nazwisko, String numer_telefonu) {
        this.id_lekarza = id_lekarza;
        this.imie_lekarza = imie;
        this.nazwisko_lekarza = nazwisko;
        this.numer_telefonu = numer_telefonu;
    }

    public int getId_lekarza() {
        return id_lekarza;
    }

    public void setId_lekarza(int id_lekarza) {
        this.id_lekarza = id_lekarza;
    }

    public String getImie_lekarza() {
        return imie_lekarza;
    }

    public void setImie_lekarza(String imie_lekarza) {
        this.imie_lekarza = imie_lekarza;
    }

    public String getNazwisko_lekarza() {
        return nazwisko_lekarza;
    }

    public void setNazwisko_lekarza(String nazwisko_lekarza) {
        this.nazwisko_lekarza = nazwisko_lekarza;
    }

    public String getNumer_telefonu() {
        return numer_telefonu;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu = numer_telefonu;
    }
}
