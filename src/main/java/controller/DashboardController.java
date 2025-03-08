package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button foodButton;

    @FXML
    private Label lable1;

    @FXML
    private Label lable2;

    @FXML
    private Button oderButton;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    public void handlefoodButton(ActionEvent event) throws IOException{
        switchToManagefoodController();
    }
    public void handleorderButton(ActionEvent event) throws IOException{
        switchToManageorderController();
    }

    public void switchToManagefoodController() throws IOException{
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Managefood.fxml"));
        Stage stage = (Stage) foodButton.getScene().getWindow();
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent,800,600);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToManageorderController() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/progastro/Manageorder.fxml"));
        Stage stage = (Stage) oderButton.getScene().getWindow();
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent,800,600);
        stage.setScene(scene);
        stage.show();
    }
}
