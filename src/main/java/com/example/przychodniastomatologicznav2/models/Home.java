package com.example.przychodniastomatologicznav2.models;

public class Home {
    private int id_pacjenta;
    private String data_wizyty, imie_lekarza, nazwisko_lekarza, imie_pacjenta, nazwisko_pacjenta, nazwa_uslugi, cena_uslugi;


    public Home(int id_pacjenta, String imie_pacjenta, String nazwisko_pacjenta, String data_wizyty, String imie_lekarza, String nazwisko_lekarza
                , String cena_uslugi, String nazwa_uslugi) {
        this.id_pacjenta = id_pacjenta;
        this.data_wizyty = data_wizyty;
        this.imie_lekarza = imie_lekarza;
        this.nazwisko_lekarza = nazwisko_lekarza;
        this.imie_pacjenta = imie_pacjenta;
        this.nazwisko_pacjenta = nazwisko_pacjenta;
        this.nazwa_uslugi = nazwa_uslugi;
        this.cena_uslugi = cena_uslugi;
    }

    public int getId_pacjenta() {
        return id_pacjenta;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
    }

    public String getData_wizyty() {
        return data_wizyty;
    }

    public void setData_wizyty(String data_wizyty) {
        this.data_wizyty = data_wizyty;
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

    public String getImie_pacjenta() {
        return imie_pacjenta;
    }

    public void setImie_pacjenta(String imie_pacjenta) {
        this.imie_pacjenta = imie_pacjenta;
    }

    public String getNazwisko_pacjenta() {
        return nazwisko_pacjenta;
    }

    public void setNazwisko_pacjenta(String nazwisko_pacjenta) {
        this.nazwisko_pacjenta = nazwisko_pacjenta;
    }

    public String getNazwa_uslugi() {
        return nazwa_uslugi;
    }

    public void setNazwa_uslugi(String nazwa_uslugi) {
        this.nazwa_uslugi = nazwa_uslugi;
    }

    public String getCena_uslugi() {
        return cena_uslugi;
    }

    public void setCena_uslugi(String cena_uslugi) {
        this.cena_uslugi = cena_uslugi;
    }
}