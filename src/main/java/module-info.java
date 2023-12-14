module com.example.miniuber {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.management;

    opens com.example.miniuber to javafx.fxml;
    exports com.example.miniuber;
    exports com.example.miniuber.classes;
    opens com.example.miniuber.classes to javafx.fxml;
}