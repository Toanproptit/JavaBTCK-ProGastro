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
import model.Account;
import model.AccountJSON;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label lable1;

    @FXML
    private Label lable2;

    @FXML
    private Button login;

    @FXML
    private Button registerButton;

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
    public boolean validateLogin(String username, String password) {

        for(int i=0;i< AccountJSON.getAccountList().size();++i){
            if (username.equals(AccountJSON.getAccountList().get(i).getAccount()) &&
                    password.equals(AccountJSON.getAccountList().get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }
    public void switchToDashBoard() throws IOException{
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Dashboard.fxml"));
        Parent dashboard = fxmlLoader.load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(dashboard,800,600));
        stage.setTitle("Dashboard - ProGastro");
        stage.show();
    }
    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void handleRegister(ActionEvent event) throws IOException{
        switchToRegister();
    }
    public void switchToRegister() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/progastro/Register.fxml"));
        Parent parent =fxmlLoader.load();
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(new Scene(parent,800,600));
        stage.setTitle("Register-ProGastro");
        stage.show();
    }
}

