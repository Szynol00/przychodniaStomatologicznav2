package com.example.przychodniastomatologicznav2.controllers;


import com.example.przychodniastomatologicznav2.dBConnect.DBConnect;
import com.example.przychodniastomatologicznav2.models.Home;
import com.example.przychodniastomatologicznav2.models.Lekarze;
import com.example.przychodniastomatologicznav2.models.Uslugi;
import com.example.przychodniastomatologicznav2.models.Wizyty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private TableColumn<Home, String> cenaUslugiCol;

    @FXML
    private TableColumn<Home, String> dataWizytyCol;

    @FXML
    private TableColumn<Home, Integer> idPacjentaCol;

    @FXML
    private TableColumn<Home, String> imieLekarzaCol;

    @FXML
    private TableColumn<Home, String> imiePacjentaCol;

    @FXML
    private TableColumn<Home, String> nazwaUslugiCol;

    @FXML
    private TableColumn<Home, String> nazwiskoLekarzaCol;

    @FXML
    private TableColumn<Home, String> nazwiskoPacjentaCol;

    @FXML
    private TableView<Home> tableViewHome;

    @FXML
    private TextField searchInfo;

    ObservableList<Home> listH;
    ObservableList<Home> dataList;


    int index = -1;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;



    public void UpdateTable() throws SQLException {
        idPacjentaCol.setCellValueFactory(new PropertyValueFactory<>("id_pacjenta"));
        imiePacjentaCol.setCellValueFactory(new PropertyValueFactory<>("imie_pacjenta"));
        nazwiskoPacjentaCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko_pacjenta"));
        dataWizytyCol.setCellValueFactory(new PropertyValueFactory<>("data_wizyty"));
        imieLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("imie_lekarza"));
        nazwiskoLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko_lekarza"));
        cenaUslugiCol.setCellValueFactory(new PropertyValueFactory<>("cena_uslugi"));
        nazwaUslugiCol.setCellValueFactory(new PropertyValueFactory<>("nazwa_uslugi"));

        listH = DBConnect.getDataHome();
        tableViewHome.setItems(listH);
    }

    @FXML
    void Search_info() throws SQLException {
        idPacjentaCol.setCellValueFactory(new PropertyValueFactory<>("id_pacjenta"));
        imiePacjentaCol.setCellValueFactory(new PropertyValueFactory<>("imie_pacjenta"));
        nazwiskoPacjentaCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko_pacjenta"));
        dataWizytyCol.setCellValueFactory(new PropertyValueFactory<>("data_wizyty"));
        imieLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("imie_lekarza"));
        nazwiskoLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko_lekarza"));
        cenaUslugiCol.setCellValueFactory(new PropertyValueFactory<>("cena_uslugi"));
        nazwaUslugiCol.setCellValueFactory(new PropertyValueFactory<>("nazwa_uslugi"));

        dataList = DBConnect.getDataHome();
        tableViewHome.setItems(dataList);

        FilteredList<Home> filteredData = new FilteredList<>(dataList, b -> true);
        searchInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(home -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (home.getImie_pacjenta().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (home.getNazwisko_pacjenta().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (home.getData_wizyty().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (home.getImie_lekarza().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (home.getNazwisko_lekarza().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (home.getNazwa_uslugi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (home.getCena_uslugi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<Home> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewHome.comparatorProperty());
        tableViewHome.setItems(sortedData);

    }

            @Override
            public void initialize (URL url, ResourceBundle resourceBundle){

                try {
                    UpdateTable();
                    Search_info();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }

        }