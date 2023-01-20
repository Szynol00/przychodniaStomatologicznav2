package com.example.przychodniastomatologicznav2.models;


public class Wizyty {
    int id_wizyty, id_lekarza, id_pacjenta, id_uslugi;
    String data_wizyty;

    public Wizyty(int id_wizyty, int id_lekarza, int id_pacjenta, int id_uslugi, String data_wizyty) {
        this.id_wizyty = id_wizyty;
        this.id_lekarza = id_lekarza;
        this.id_pacjenta = id_pacjenta;
        this.id_uslugi = id_uslugi;
        this.data_wizyty = data_wizyty;
    }

    public int getId_wizyty() {
        return id_wizyty;
    }

    public void setId_wizyty(int id_wizyty) {
        this.id_wizyty = id_wizyty;
    }

    public int getId_lekarza() {
        return id_lekarza;
    }

    public void setId_lekarza(int id_lekarza) {
        this.id_lekarza = id_lekarza;
    }

    public int getId_pacjenta() {
        return id_pacjenta;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
    }

    public int getId_uslugi() {
        return id_uslugi;
    }

    public void setId_uslugi(int id_uslugi) {
        this.id_uslugi = id_uslugi;
    }

    public String getData_wizyty() {
        return data_wizyty;
    }

    public void setData_wizyty(String data_wizyty) {
        this.data_wizyty = data_wizyty;
    }
};