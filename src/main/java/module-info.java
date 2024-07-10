module com.example.week10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.week10 to javafx.fxml;
    exports com.example.week10;
}