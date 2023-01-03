module com.example.przychodniastomatologicznav2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.przychodniastomatologicznav2 to javafx.fxml;
    exports com.example.przychodniastomatologicznav2;
}