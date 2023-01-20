package com.example.przychodniastomatologicznav2.models;

public class Lekarze {
    int id_lekarza;
    String imie, nazwisko, specjalizacja, numer_telefonu;

    public Lekarze(int id_lekarza, String imie, String nazwisko, String specjalizacja, String numer_telefonu) {
        this.id_lekarza = id_lekarza;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.specjalizacja = specjalizacja;
        this.numer_telefonu = numer_telefonu;
    }

    public int getId_lekarza() {
        return id_lekarza;
    }

    public void setId_lekarza(int id_lekarza) {
        this.id_lekarza = id_lekarza;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public String getNumer_telefonu() {
        return numer_telefonu;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu = numer_telefonu;
    }
}
