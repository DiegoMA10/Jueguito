module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    exports com.example.entity;
    exports com.example.entity.npc;
    opens com.example to javafx.fxml;
    exports com.example;
}
