package com.example.przychodniastomatologicznav2.models;

public class Uslugi {

  int id_uslugi;
  String nazwa_uslugi, cena, specjalizacja;

  public Uslugi(int id_uslugi, String nazwa_uslugi, String cena, String specjalizacja) {
    this.id_uslugi = id_uslugi;
    this.nazwa_uslugi = nazwa_uslugi;
    this.cena = cena;
    this.specjalizacja = specjalizacja;
  }

  public int getId_uslugi() {
    return id_uslugi;
  }

  public void setId_uslugi(int id_uslugi) {
    this.id_uslugi = id_uslugi;
  }

  public String getNazwa_uslugi() {
    return nazwa_uslugi;
  }

  public void setNazwa_uslugi(String nazwa_uslugi) {
    this.nazwa_uslugi = nazwa_uslugi;
  }

  public String getCena() {
    return cena;
  }

  public void setCena(String cena) {
    this.cena = cena;
  }

  public String getSpecjalizacja() {
    return specjalizacja;
  }

  public void setSpecjalizacja(String specjalizacja) {
    this.specjalizacja = specjalizacja;
  }
}
