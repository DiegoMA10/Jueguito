module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    exports com.example.entity;

    opens com.example to javafx.fxml;
    exports com.example;
}
