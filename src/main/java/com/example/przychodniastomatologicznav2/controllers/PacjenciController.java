package com.example.przychodniastomatologicznav2.controllers;

import com.example.przychodniastomatologicznav2.dBConnect.DBConnect;
import com.example.przychodniastomatologicznav2.models.Lekarze;
import com.example.przychodniastomatologicznav2.models.Pacjenci;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

public class PacjenciController implements Initializable {
    @FXML
    private TableColumn<Pacjenci, String> adrespacjentaCol;

    @FXML
    private TableColumn<Pacjenci, Integer> idPacjentaCol;

    @FXML
    private TableColumn<Pacjenci, String> imiePacjentaCol;

    @FXML
    private TableColumn<Pacjenci, String> kodPoczotwyCol;

    @FXML
    private TableColumn<Pacjenci, String> miastoCol;

    @FXML
    private TableColumn<Pacjenci, String> nazwiskoCol;

    @FXML
    private TableColumn<Pacjenci, String> nrTelefonuPacjentaCol;

    @FXML
    private TableColumn<Pacjenci, String> peselCol;

    @FXML
    private TableView<Pacjenci> tableViewPacjenci;

    @FXML
    private TextField numerTelPacTxt, nazwiskoPacTxt, miastoPacTxt, kodPoczPacTxt, imiePacTxt, adresPacTxt, idPacTxt, peselPacTxt;

    @FXML
    private TextField searchInfo;


    int index = -1;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    ObservableList<Pacjenci> listP;
    ObservableList<Pacjenci> dataList;

    public void Add() {
        connection = DBConnect.ConnectDb();
        String query = "INSERT INTO pacjenci (imie, nazwisko, pesel, adres_zamieszkania, kod_pocztowy, miasto, numer_telefonu) VALUES (?,?,?,?,?,?,?)";
        try {
            if(imiePacTxt.getText().equals("") || nazwiskoPacTxt.getText().equals("") || peselPacTxt.getText().equals("") ||
                    adresPacTxt.getText().equals("") || kodPoczPacTxt.getText().equals("") || miastoPacTxt.getText().equals("") ||
                    numerTelPacTxt.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Uwaga");
                alert.setHeaderText(null);
                alert.setContentText("Nie można dodać pacjenta z pustymi polami");
                alert.showAndWait();
            } else {
                pst = connection.prepareStatement(query);
                pst.setString(1, imiePacTxt.getText());
                pst.setString(2, nazwiskoPacTxt.getText());
                pst.setString(3, peselPacTxt.getText());
                pst.setString(4, adresPacTxt.getText());
                pst.setString(5, kodPoczPacTxt.getText());
                pst.setString(6, miastoPacTxt.getText());
                pst.setString(7, numerTelPacTxt.getText());
                pst.execute();
                UpdateTable();
                SearchInfo();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dodano pacjenta");
                alert.setHeaderText(null);
                alert.setContentText("Pacjent został dodany");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie można dodać pacjenta! Upewnij się, że podałeś poprawne dane");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void getSelected() {
        index = tableViewPacjenci.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idPacTxt.setText(idPacjentaCol.getCellData(index).toString());
        imiePacTxt.setText(imiePacjentaCol.getCellData(index).toString());
        nazwiskoPacTxt.setText(nazwiskoCol.getCellData(index).toString());
        peselPacTxt.setText(peselCol.getCellData(index).toString());
        adresPacTxt.setText(adrespacjentaCol.getCellData(index).toString());
        kodPoczPacTxt.setText(kodPoczotwyCol.getCellData(index).toString());
        miastoPacTxt.setText(miastoCol.getCellData(index).toString());
        numerTelPacTxt.setText(nrTelefonuPacjentaCol.getCellData(index).toString());
    }

    public void Delete() {
        connection = DBConnect.ConnectDb();
        String query = "DELETE FROM pacjenci WHERE id_pacjenta = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, idPacTxt.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Usunięto pacjenta");
            alert.showAndWait();
            UpdateTable();
            SearchInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Edit() {
        try {
            connection = DBConnect.ConnectDb();
            String value1 = idPacTxt.getText();
            String value2 = imiePacTxt.getText();
            String value3 = nazwiskoPacTxt.getText();
            String value4 = peselPacTxt.getText();
            String value5 = adresPacTxt.getText();
            String value6 = kodPoczPacTxt.getText();
            String value7 = miastoPacTxt.getText();
            String value8 = numerTelPacTxt.getText();
            String query = "UPDATE pacjenci SET id_pacjenta = '" + value1 + "', imie = '" + value2 + "', " +
                    "nazwisko = '" + value3 + "', pesel = '" + value4 + "', adres_zamieszkania = '" + value5 + "'," +
                    " kod_pocztowy = '" + value6 + "', miasto = '" + value7 + "', " +
                    "numer_telefonu = '" + value8 + "' WHERE id_pacjenta = '" + value1 + "'";
            pst = connection.prepareStatement(query);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Zaktualizowano dane pacjenta");
            alert.showAndWait();
            UpdateTable();
            SearchInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateTable() throws SQLException {
        idPacjentaCol.setCellValueFactory(new PropertyValueFactory<>("id_pacjenta"));
        imiePacjentaCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
        nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        peselCol.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        adrespacjentaCol.setCellValueFactory(new PropertyValueFactory<>("adres_zamieszkania"));
        kodPoczotwyCol.setCellValueFactory(new PropertyValueFactory<>("kod_pocztowy"));
        miastoCol.setCellValueFactory(new PropertyValueFactory<>("miasto"));
        nrTelefonuPacjentaCol.setCellValueFactory(new PropertyValueFactory<>("numer_telefonu"));

        listP = DBConnect.getDataPacjenci();
        tableViewPacjenci.setItems(listP);
    }

    public void SearchInfo() throws SQLException {
        connection = DBConnect.ConnectDb();
        dataList = DBConnect.getDataPacjenci();
        tableViewPacjenci.setItems(dataList);
        FilteredList<Pacjenci> filteredData = new FilteredList<>(dataList, b -> true);
        searchInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pacjenci -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (pacjenci.getImie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (pacjenci.getNazwisko().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (pacjenci.getPesel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (pacjenci.getAdres_zamieszkania().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (pacjenci.getKod_pocztowy().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (pacjenci.getMiasto().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (pacjenci.getNumer_telefonu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Pacjenci> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewPacjenci.comparatorProperty());
        tableViewPacjenci.setItems(sortedData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            UpdateTable();
            SearchInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
