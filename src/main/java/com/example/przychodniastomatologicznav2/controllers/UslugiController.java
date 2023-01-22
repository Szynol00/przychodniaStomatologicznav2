package com.example.przychodniastomatologicznav2.controllers;

import com.example.przychodniastomatologicznav2.dBConnect.DBConnect;
import com.example.przychodniastomatologicznav2.models.Pacjenci;
import com.example.przychodniastomatologicznav2.models.Uslugi;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UslugiController implements Initializable {

    @FXML
    private TableColumn<Uslugi, String> cenaUslugiCol;

    @FXML
    private TextField cenaUslugiTxt;

    @FXML
    private TableColumn<Uslugi, Integer> idUslugiCol;

    @FXML
    private TextField idUslugiTxt;

    @FXML
    private TableColumn<Uslugi, String> nazwaUslugiCol;

    @FXML
    private TextField nazwaUslugiTxt;
    @FXML
    private TableView<Uslugi> tableViewUslugi;

    @FXML
    private TextField searchInfo;

    int index = -1;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    ObservableList<Uslugi> listU;
    ObservableList<Uslugi> dataList;


    @FXML
    void Add(ActionEvent event) {
        Connection connection = DBConnect.ConnectDb();
        String sql = "INSERT INTO uslugi (nazwa_uslugi, cena) VALUES (?,?)";
        try {
            if(nazwaUslugiTxt.getText().equals("") || cenaUslugiTxt.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Wypełnij wszystkie pola");
                alert.showAndWait();
            }else {
                pst = connection.prepareStatement(sql);
                pst.setString(1, nazwaUslugiTxt.getText());
                pst.setString(2, cenaUslugiTxt.getText());
                pst.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Dodano usługę");
                alert.showAndWait();
                UpdateTable();
                SearchInfo();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        Connection connection = DBConnect.ConnectDb();
        String sql = "DELETE FROM uslugi WHERE id_uslugi = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, idUslugiTxt.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Usunięto usługę");
            alert.showAndWait();
            UpdateTable();
            SearchInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Edit(ActionEvent event) {
        Connection connection = DBConnect.ConnectDb();
        String sql = "UPDATE uslugi SET nazwa_uslugi=?, cena=? WHERE id_uslugi=?";
        try {
            if(nazwaUslugiTxt.getText().equals("") || cenaUslugiTxt.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Wypełnij wszystkie pola");
                alert.showAndWait();
            }else {
                pst = connection.prepareStatement(sql);
                pst.setString(1, nazwaUslugiTxt.getText());
                pst.setString(2, cenaUslugiTxt.getText());
                pst.setString(3, idUslugiTxt.getText());
                pst.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Zaktualizowano usługę");
                alert.showAndWait();
                UpdateTable();
                SearchInfo();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void getSelected(MouseEvent event) {
        index = tableViewUslugi.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idUslugiTxt.setText(idUslugiCol.getCellData(index).toString());
        nazwaUslugiTxt.setText(nazwaUslugiCol.getCellData(index));
        cenaUslugiTxt.setText(cenaUslugiCol.getCellData(index));
    }



    public void UpdateTable() throws SQLException {
        idUslugiCol.setCellValueFactory(new PropertyValueFactory<Uslugi, Integer>("id_uslugi"));
        nazwaUslugiCol.setCellValueFactory(new PropertyValueFactory<Uslugi, String>("nazwa_uslugi"));
        cenaUslugiCol.setCellValueFactory(new PropertyValueFactory<Uslugi, String>("cena"));

        listU = DBConnect.getDataUslugi();
        tableViewUslugi.setItems(listU);
    }

    public void SearchInfo() throws SQLException {
        connection = DBConnect.ConnectDb();
        dataList = DBConnect.getDataUslugi();
        tableViewUslugi.setItems(dataList);
        FilteredList<Uslugi> filteredData = new FilteredList<>(dataList, b -> true);
        searchInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(uslugi -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (uslugi.getNazwa_uslugi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (uslugi.getCena().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(uslugi.getId_uslugi()).indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<Uslugi> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewUslugi.comparatorProperty());
        tableViewUslugi.setItems(sortedData);
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
