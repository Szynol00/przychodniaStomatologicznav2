package com.example.przychodniastomatologicznav2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane borderPane1;

    @FXML
    private TabPane TabCennik, tabLekarze, tabPacjenci, tabWizyty, tabCennik;

    @FXML
    private Button btnCennik, btnLekarze, btnPacjenci, btnStatusLewy, btnWizyty, btnHome;

    @FXML
    private Label labelStatus;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnHome.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
                borderPane1.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnWizyty.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("wizyty.fxml"));
                borderPane1.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnPacjenci.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("pacjenci.fxml"));
                borderPane1.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnLekarze.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("lekarze.fxml"));
                borderPane1.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCennik.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("uslugi.fxml"));
                borderPane1.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnHome.fire();
    }
@FXML
    private void handleClicks(ActionEvent event) {

    }





}
