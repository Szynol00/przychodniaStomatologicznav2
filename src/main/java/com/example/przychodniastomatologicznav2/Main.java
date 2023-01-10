package com.example.przychodniastomatologicznav2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    double x, y = 0;
    @Override
    public void start(Stage stage) throws IOException {

        StackPane stackPane = new StackPane();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        Scene scene = new Scene((root));
//        stage.initStyle(StageStyle.UNDECORATED);

        //poruszanie okna po przytrzymaniu myszki
        root.setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}