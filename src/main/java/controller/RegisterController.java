package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Account;
import model.AccountJSON;

import java.io.IOException;

public class RegisterController {

    @FXML
    private Button backToLoginButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private GridPane account;

    @FXML
    private TextField accountField;

    @FXML
    private Text fullName;

    @FXML
    private TextField fullNameField;

    @FXML
    private Text password;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text passwordagain;

    @FXML
    private TextField phoneField;

    @FXML
    private Text phonenumber;

    @FXML
    private Button registerButton;

    @FXML
    public void handleRegister(ActionEvent event)throws IOException{
        String account = accountField.getText();
        String password= passwordField.getText();
        Account newAccount = new Account(account,password);
        AccountJSON.addAccount(newAccount);
        showAlert("Thông báo","Đăng kí thành công");
    }
    @FXML
    public void handleLogin(ActionEvent event)throws IOException{switchToLogin();}

    @FXML
    public void switchToLogin() throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Login.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) backToLoginButton.getScene().getWindow();
        Scene scene = new Scene(parent,800,600);
        stage.setScene(scene);
        stage.setTitle("Login-ProGastro");
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
