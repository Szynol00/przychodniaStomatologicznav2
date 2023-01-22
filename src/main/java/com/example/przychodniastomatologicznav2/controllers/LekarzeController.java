package com.example.przychodniastomatologicznav2.controllers;

import com.example.przychodniastomatologicznav2.dBConnect.DBConnect;
import com.example.przychodniastomatologicznav2.models.Lekarze;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LekarzeController implements Initializable {


    @FXML
    private TableColumn<Lekarze, Integer> idLekarzaCol;

    @FXML
    private TableColumn<Lekarze, String> imieLekarzaCol;

    @FXML
    private TableColumn<Lekarze, String> nazwiskoLekarzaCol;

    @FXML
    private TableColumn<Lekarze, String> nrTelefonuCol;

    @FXML
    private TableView<Lekarze> tableViewLekarze;

    @FXML
    private TextField idLekTxt, numerTelLekTxt, imieLekTxt,nazwiskoLekTxt;

    @FXML
    private TextField searchInfo;
    ObservableList<Lekarze> listL;
    ObservableList<Lekarze> dataList;

    int index = -1;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void AddDoctor(){
        connection = DBConnect.ConnectDb();
        String sql = "insert into lekarze (imie_lekarza, nazwisko_lekarza, numer_telefonu) values (?,?,?)";
          try {
              if(imieLekTxt.getText().equals("") || nazwiskoLekTxt.getText().equals("") || numerTelLekTxt.getText().equals("")){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText(null);
                  alert.setContentText("Nie można dodać lekarza z pustymi polami");
                  alert.showAndWait();
              }else {
                  pst = connection.prepareStatement(sql);
                  pst.setString(1, imieLekTxt.getText());
                  pst.setString(2, nazwiskoLekTxt.getText());
                  pst.setString(3, numerTelLekTxt.getText());
                  pst.execute();
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setHeaderText(null);
                  alert.setContentText("Dodano lekarza");
                  alert.showAndWait();
                  UpdateTable();
                  SearchInfo();
            }

              } catch (SQLException e) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setHeaderText(null);
              alert.setContentText("Błąd podczas dodawania lekarza");
              alert.showAndWait();
                e.printStackTrace();
          }
             }
    public void getSelected(){
        int index = tableViewLekarze.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        idLekTxt.setText(idLekarzaCol.getCellData(index).toString());
        imieLekTxt.setText(imieLekarzaCol.getCellData(index).toString());
        nazwiskoLekTxt.setText(nazwiskoLekarzaCol.getCellData(index).toString());
        numerTelLekTxt.setText(nrTelefonuCol.getCellData(index).toString());
    }

    public void EditDoctor() {
        try {
            connection = DBConnect.ConnectDb();
            String value1 = idLekTxt.getText();
            String value2 = imieLekTxt.getText();
            String value3 = nazwiskoLekTxt.getText();
            String value4 = numerTelLekTxt.getText();
            String sql = "update lekarze set id_lekarza='" + value1 + "',imie_lekarza='" + value2 + "',nazwisko_lekarza='" + value3 + "',numer_telefonu='" + value4 +
                    "' where id_lekarza='" + value1 + "'";
            pst = connection.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Zaktualizowano dane lekarza");
            alert.showAndWait();
            UpdateTable();
            SearchInfo();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie można dodać lekarza z pustymi polami");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void DeleteDoctor() {
        connection = DBConnect.ConnectDb();
        String sql = "delete from lekarze where id_lekarza=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, idLekTxt.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Usunięto lekarza");
            alert.showAndWait();
            UpdateTable();
            SearchInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void UpdateTable() throws SQLException {
        idLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("id_lekarza"));
        imieLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("imie_lekarza"));
        nazwiskoLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko_lekarza"));
        nrTelefonuCol.setCellValueFactory(new PropertyValueFactory<>("numer_telefonu"));

        listL = DBConnect.getDataLekarze();
        tableViewLekarze.setItems(listL);
    }

    public void SearchInfo() throws SQLException {
        connection = DBConnect.ConnectDb();
        dataList = DBConnect.getDataLekarze();
        tableViewLekarze.setItems(dataList);
        FilteredList<Lekarze> filteredData = new FilteredList<>(dataList, b -> true);
        searchInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(lekarze -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (lekarze.getImie_lekarza().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (lekarze.getNazwisko_lekarza().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (lekarze.getNumer_telefonu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Lekarze> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableViewLekarze.comparatorProperty());
        tableViewLekarze.setItems(sortedData);
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
