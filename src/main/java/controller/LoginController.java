package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label lable1;

    @FXML
    private Label lable2;

    @FXML
    private Button login;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    public void handleLogin(ActionEvent event) throws IOException{
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(validateLogin(username,password)){
            switchToDashBoard();
        }
        else {
            showAlert("Đăng nhập thất bại", "Sai tài khoản hoặc mật khẩu!");
        }
    }
    private boolean validateLogin(String username, String password) {
        return username.equals("admin") && password.equals("12345"); // Tạm thời hard-code
    }
    private void switchToDashBoard() throws IOException{
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Dashboard.fxml"));
        Parent dashboardview = fxmlLoader.load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(dashboardview,800,600));
        stage.setTitle("Dashboard - ProGastro");
        stage.show();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

