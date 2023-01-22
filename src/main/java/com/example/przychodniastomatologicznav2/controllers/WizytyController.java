package com.example.przychodniastomatologicznav2.controllers;


import com.example.przychodniastomatologicznav2.dBConnect.DBConnect;
import com.example.przychodniastomatologicznav2.models.Uslugi;
import com.example.przychodniastomatologicznav2.models.Wizyty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WizytyController implements Initializable {

    @FXML
    private TableColumn<Wizyty, String> dataWizytyCol;

    @FXML
    private TextField dataWizytytxt;

    @FXML
    private TableColumn<?, ?> idLekarzaCol;

    @FXML
    private TextField idLekarzaTxt;

    @FXML
    private TableColumn<Wizyty, Integer> idPacjentaCol;

    @FXML
    private TextField idPacjentaTxt;

    @FXML
    private TableColumn<Wizyty, Integer> idUslugiCol;

    @FXML
    private TextField idUslugiTxt;

    @FXML
    private TableColumn<Wizyty, Integer> idWizytyCol;

    @FXML
    private TextField idWyzytyTxtx;

    @FXML
    private TableView<Wizyty> tableViewWizyty;

    int index = -1;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    ObservableList<Wizyty> listW;


    @FXML
    void Add(ActionEvent event) throws SQLException {
        Connection connection = DBConnect.ConnectDb();
        String sql = "INSERT INTO wizyty (id_pacjenta, id_lekarza, id_uslugi, data_wizyty) VALUES (?,?,?,?)";

        int count = 0;
        String checkPacjent = "SELECT COUNT(*) FROM pacjenci WHERE id_pacjenta = ?";
        pst = connection.prepareStatement(checkPacjent);
        pst.setString(1, idPacjentaTxt.getText());
        rs = pst.executeQuery();
        while(rs.next()){
            count = rs.getInt(1);
        }
        if(count == 0){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setHeaderText(null);
            alert2.setContentText("Nie ma pacjenta o podanym ID! Upewnij się czy podany pacjent istnieje w bazie danych.");
            alert2.showAndWait();
            return;
        }
        count = 0;
        String checkLekarz= "SELECT COUNT(*) FROM lekarze WHERE id_lekarza = ?";
        pst = connection.prepareStatement(checkLekarz);
        pst.setString(1, idLekarzaTxt.getText());
        rs = pst.executeQuery();
        while(rs.next()){
            count = rs.getInt(1);
        }
        if(count == 0){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setHeaderText(null);
            alert2.setContentText("Nie ma lekarza o podanym ID! Upewnij się czy podany pacjent istnieje w bazie danych.");
            alert2.showAndWait();
            return;
        }

        count = 0;
        String checkUslugi= "SELECT COUNT(*) FROM uslugi WHERE id_uslugi = ?";
        pst = connection.prepareStatement(checkUslugi);
        pst.setString(1, idUslugiTxt.getText());
        rs = pst.executeQuery();
        while(rs.next()){
            count = rs.getInt(1);
        }
        if(count == 0){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setHeaderText(null);
            alert2.setContentText("Nie ma uslugi o podanym ID! Upewnij się czy podana usluga istnieje w bazie danych.");
            alert2.showAndWait();
            return;
        }

        try {
            if(idPacjentaTxt.getText().equals("") || idLekarzaTxt.getText().equals("") || idUslugiTxt.getText().equals("") || dataWizytytxt.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Wypełnij wszystkie pola");
                alert.showAndWait();

            }else{
                pst = connection.prepareStatement(sql);
                pst.setString(1, idPacjentaTxt.getText());
                pst.setString(2, idLekarzaTxt.getText());
                pst.setString(3, idUslugiTxt.getText());
                pst.setString(4, dataWizytytxt.getText());
                pst.execute();
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Dodano");
                alert2.setHeaderText(null);
                alert2.setContentText("Dodano wizytę");
                alert2.showAndWait();
                UpdateTable();
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie udało się dodać wizyty!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        Connection connection = DBConnect.ConnectDb();
        String sql = "DELETE FROM wizyty WHERE id_wizyty = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, idWyzytyTxtx.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Usunięto");
            alert.setHeaderText(null);
            alert.setContentText("Usunięto wizytę");
            alert.showAndWait();
            UpdateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Edit(ActionEvent event) {
        Connection connection = DBConnect.ConnectDb();
        String sql = "UPDATE wizyty SET id_pacjenta = ?, id_lekarza = ?, id_uslugi = ?, data_wizyty = ? WHERE id_wizyty = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, idPacjentaTxt.getText());
            pst.setString(2, idLekarzaTxt.getText());
            pst.setString(3, idUslugiTxt.getText());
            pst.setString(4, dataWizytytxt.getText());
            pst.setString(5, idWyzytyTxtx.getText());
            pst.execute();
            UpdateTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edytowano");
            alert.setHeaderText(null);
            alert.setContentText("Edytowano wizytę");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getSelected(MouseEvent event) {
        index = tableViewWizyty.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idWyzytyTxtx.setText(idWizytyCol.getCellData(index).toString());
        idPacjentaTxt.setText(idPacjentaCol.getCellData(index).toString());
        idLekarzaTxt.setText(idLekarzaCol.getCellData(index).toString());
        idUslugiTxt.setText(idUslugiCol.getCellData(index).toString());
        dataWizytytxt.setText(dataWizytyCol.getCellData(index).toString());
    }

    public void UpdateTable() throws SQLException {
        idWizytyCol.setCellValueFactory(new PropertyValueFactory<>("id_wizyty"));
        idLekarzaCol.setCellValueFactory(new PropertyValueFactory<>("id_lekarza"));
        idPacjentaCol.setCellValueFactory(new PropertyValueFactory<>("id_pacjenta"));
        idUslugiCol.setCellValueFactory(new PropertyValueFactory<>("id_uslugi"));
        dataWizytyCol.setCellValueFactory(new PropertyValueFactory<>("data_wizyty"));

        listW = DBConnect.getDataWizyty();
        tableViewWizyty.setItems(listW);
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
