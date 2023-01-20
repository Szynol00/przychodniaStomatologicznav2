package com.example.przychodniastomatologicznav2.controllers;

import com.example.przychodniastomatologicznav2.dBConnect.DBConnect;
import com.example.przychodniastomatologicznav2.models.Lekarze;
import javafx.collections.ObservableList;
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
    private Label labelStatus;

    @FXML
    private TableColumn<Lekarze, String> nazwiskoLekarzaCol;

    @FXML
    private TableColumn<Lekarze, String> nrTelefonuCol;

    @FXML
    private TableColumn<Lekarze, String> specialnoscLakarzaCol;

    @FXML
    private TableView<Lekarze> tableViewLekarze;

    @FXML
    private TextField idLekTxt, specLekTxt, numerTelLekTxt, imieLekTxt,nazwiskoLekTxt;

    ObservableList<Lekarze> listL;

    int index = -1;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void AddDoctor(){
        connection = DBConnect.ConnectDb();
        String sql = "insert into lekarze (imie, nazwisko,specjalizacja, numer_telefonu) values (?,?,?,?)";
          try {
              if(imieLekTxt.getText().equals("") || nazwiskoLekTxt.getText().equals("") || specLekTxt.getText().equals("") || numerTelLekTxt.getText().equals("")){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText(null);
                  alert.setContentText("Nie można dodać lekarza z pustymi polami");
                  alert.showAndWait();
              }else {
                  pst = connection.prepareStatement(sql);
                  pst.setString(1, imieLekTxt.getText());
                  pst.setString(2, nazwiskoLekTxt.getText());
                  pst.setString(3, specLekTxt.getText());
                  pst.setString(4, numerTelLekTxt.getText());
                  pst.execute();
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setHeaderText(null);
                  alert.setContentText("Dodano lekarza");
                  alert.showAndWait();
                  UpdateTable();
            }

              } catch (SQLException e) {
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
        specLekTxt.setText(specialnoscLakarzaCol.getCellData(index).toString());
        numerTelLekTxt.setText(nrTelefonuCol.getCellData(index).toString());
    }

    public void EditDoctor() {
        try {
            connection = DBConnect.ConnectDb();
            String value1 = idLekTxt.getText();
            String value2 = imieLekTxt.getText();
            String value3 = nazwiskoLekTxt.getText();
            String value4 = specLekTxt.getText();
            String value5 = numerTelLekTxt.getText();
            String sql = "update lekarze set id_lekarza='" + value1 + "',imie='" + value2 + "',nazwisko='" + value3 + "'," +
                    "specjalizacja='" + value4 + "',numer_telefonu='" + value5 + "' where id_lekarza='" + value1 + "'";
            pst = connection.prepareStatement(sql);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Zaktualizowano dane lekarza");
            alert.showAndWait();
            UpdateTable();
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void UpdateTable() throws SQLException {
        idLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("id_lekarza"));
        imieLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
        nazwiskoLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        specialnoscLakarzaCol.setCellValueFactory(new PropertyValueFactory<>("specjalizacja"));
        nrTelefonuCol.setCellValueFactory(new PropertyValueFactory<>("numer_telefonu"));

        listL = DBConnect.getDataLekarze();
        tableViewLekarze.setItems(listL);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
