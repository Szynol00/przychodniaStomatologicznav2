package com.example.przychodniastomatologicznav2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnCennik;

    @FXML
    private Button btnLekarze;

    @FXML
    private Button btnPacjenci;

    @FXML
    private Button btnStatusLewy;

    @FXML
    private Button btnStatystyki;

    @FXML
    private Button btnWizyty;

    @FXML
    private Label labelStatus;

    @FXML
    private TabPane tabStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
@FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == btnWizyty){
            labelStatus.setText("Wizyty");
        }
        else if(event.getSource() == btnPacjenci){
            labelStatus.setText("Pacjęci");
            btnStatusLewy.setText("Dodaj pacjęta");
        }
        else if(event.getSource() == btnLekarze){
            labelStatus.setText("Lekarze");
            btnStatusLewy.setText("Dodaj lekarza");
        }
        else if(event.getSource() == btnCennik){
            labelStatus.setText("Cennik");
            btnStatusLewy.setText("Dodaj usługę");
        }
        else if(event.getSource() == btnStatystyki){
            labelStatus.setText("Statystyki");
        }
    }
}