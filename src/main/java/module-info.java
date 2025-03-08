module org.example.progastro {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.progastro to javafx.fxml;
    exports org.example.progastro;
    exports controller;
    opens controller to javafx.fxml;
}