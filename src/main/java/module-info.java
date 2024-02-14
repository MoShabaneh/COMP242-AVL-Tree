module org.example.datastruct3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.datastruct3 to javafx.fxml;
    exports org.example.datastruct3;
}