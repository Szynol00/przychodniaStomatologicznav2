package com.example.przychodniastomatologicznav2.models;

public class Uslugi {

  private int id_uslugi;
  private String nazwa_uslugi, cena;

  public Uslugi(int id_uslugi, String nazwa_uslugi, String cena) {
    this.id_uslugi = id_uslugi;
    this.nazwa_uslugi = nazwa_uslugi;
    this.cena = cena;
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

}
