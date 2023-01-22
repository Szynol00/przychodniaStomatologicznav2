package com.example.przychodniastomatologicznav2.models;

public class Pacjenci {

    int id_pacjenta;
    String imie, nazwisko, pesel, numer_telefonu, adres_zamieszkania, kod_pocztowy, miasto;

    public Pacjenci(int id, String imie, String nazwisko, String pesel, String adres, String kodPocztowy, String miasto, String telefon) {
        this.id_pacjenta = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.numer_telefonu = telefon;
        this.adres_zamieszkania = adres;
        this.kod_pocztowy = kodPocztowy;
        this.miasto = miasto;
    }

    public int getId_pacjenta() {
        return id_pacjenta;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNumer_telefonu() {
        return numer_telefonu;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu = numer_telefonu;
    }

    public String getAdres_zamieszkania() {
        return adres_zamieszkania;
    }

    public void setAdres_zamieszkania(String adres_zamieszkania) {
        this.adres_zamieszkania = adres_zamieszkania;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }
}