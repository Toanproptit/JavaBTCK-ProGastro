package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageorderController {
    @FXML
    private Button backbutton;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private TableView<?> tableview;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    public void handle(ActionEvent event)throws IOException {
        switchToDashBoard();
    }

    private void switchToDashBoard() throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Dashboard.fxml"));
        Parent dashboardview = fxmlLoader.load();
        Stage stage = (Stage) backbutton.getScene().getWindow();
        stage.setScene(new Scene(dashboardview,800,600));
        stage.setTitle("Dashboard - ProGastro");
        stage.show();
    }

}
