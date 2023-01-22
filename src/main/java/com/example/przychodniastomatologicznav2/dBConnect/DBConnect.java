package com.example.przychodniastomatologicznav2.dBConnect;

import com.example.przychodniastomatologicznav2.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.util.Date;

import java.sql.*;
import java.text.SimpleDateFormat;

public class DBConnect {

    Connection connection = null;

    public static Connection ConnectDb() {
        String url = "jdbc:sqlite:C:\\Users\\calka\\Desktop\\SQLite\\gabinet.db";
        try {
            Connection connection = (Connection) DriverManager.getConnection(url);
            System.out.println("Połączono się z bazą danych!");
            return connection;

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie można połączyć się z bazą danych!");
            alert.showAndWait();
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<Pacjenci> getDataPacjenci() throws SQLException {
        Connection connection = ConnectDb();
        ObservableList<Pacjenci> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM pacjenci");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Pacjenci(Integer.parseInt(rs.getString("id_pacjenta")),
                        rs.getString("imie"),
                        rs.getString("nazwisko"),
                        rs.getString("pesel"),
                        rs.getString("adres_zamieszkania"),
                        rs.getString("kod_pocztowy"),
                        rs.getString("miasto"),
                        rs.getString("numer_telefonu")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return list;
    }


    public static ObservableList<Lekarze> getDataLekarze() throws SQLException {
        Connection connection = ConnectDb();
        ObservableList<Lekarze> list2 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM lekarze");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list2.add(new Lekarze(Integer.parseInt(rs.getString("id_lekarza")),
                        rs.getString("imie_lekarza"),
                        rs.getString("nazwisko_lekarza"),
                        rs.getString("numer_telefonu")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return list2;
        }
    }

    public static ObservableList<Uslugi> getDataUslugi() throws SQLException {
        Connection connection = ConnectDb();
        ObservableList<Uslugi> list3 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM uslugi");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list3.add(new Uslugi(Integer.parseInt(rs.getString("id_uslugi")),
                        rs.getString("nazwa_uslugi"),
                        rs.getString("cena")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
            return list3;
        }
    }

    public static ObservableList<Wizyty> getDataWizyty() throws SQLException {
        Connection connection = ConnectDb();
        ObservableList<Wizyty> list4 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM wizyty");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list4.add(new Wizyty(Integer.parseInt(rs.getString("id_wizyty")),
                        Integer.parseInt(rs.getString("id_lekarza")),
                        Integer.parseInt(rs.getString("id_pacjenta")),
                        Integer.parseInt(rs.getString("id_uslugi")),
                        rs.getString("data_wizyty")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
            return list4;
        }
    }

    public static ObservableList<Home> getDataHome() throws SQLException {
        Connection connection = ConnectDb();
        ObservableList<Home> list5 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT pacjenci.id_pacjenta, pacjenci.imie, pacjenci.nazwisko, wizyty.data_wizyty, " +
                    "lekarze.imie_lekarza, lekarze.nazwisko_lekarza, uslugi.cena, uslugi.nazwa_uslugi\n" +
                    "FROM wizyty\n" +
                    "JOIN pacjenci ON wizyty.id_pacjenta = pacjenci.id_pacjenta\n" +
                    "JOIN lekarze ON wizyty.id_lekarza = lekarze.id_lekarza\n" +
                    "JOIN uslugi ON wizyty.id_uslugi = uslugi.id_uslugi;\n" +
                    "\n");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list5.add(new Home(Integer.parseInt(rs.getString("id_pacjenta")),
                        rs.getString("imie"),
                        rs.getString("nazwisko"),
                        rs.getString("data_wizyty"),
                        rs.getString("imie_lekarza"),
                        rs.getString("nazwisko_lekarza"),
                        rs.getString("cena"),
                rs.getString("nazwa_uslugi")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
            return list5;
        }
    }


}