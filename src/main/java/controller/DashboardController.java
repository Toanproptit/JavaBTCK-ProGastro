package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.BackgroundImageManager;

import java.io.File;
import java.io.IOException;

public class DashboardController {
    @FXML
    private AnchorPane root;

    @FXML
    public Button orderButton;

    @FXML
    private Button foodButton;

    @FXML
    private Label lable1;

    @FXML
    private Label lable2;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    public void initialize() {
        // Tải ảnh nền cho màn hình này (stageId = "editFoodStage") khi mở ứng dụng
        String imagePath = BackgroundImageManager.loadBackgroundImageForStage("dashBoard");
        if (!imagePath.isEmpty()) {
            root.setStyle("-fx-background-image: url('" + imagePath + "'); -fx-background-size: cover; -fx-background-position: center center;");
        }
    }

    @FXML
    public void handleChangeBackgroundImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String imagePath = selectedFile.toURI().toString();

            root.setStyle("-fx-background-image: url('" + imagePath + "'); -fx-background-size: cover; -fx-background-position: center center;");
            try {
                BackgroundImageManager.saveBackgroundImage("dashBoard",imagePath);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Lỗi", "Không thể lưu ảnh nền");
            }
        }
    }

    @FXML
    public void handlefoodButton(ActionEvent event) throws IOException{
        switchToManageFoodController();
    }
    @FXML
    public void handleorderButton(ActionEvent event) throws IOException{
        switchToManageOrderController();
    }

    private void switchToManageFoodController() throws IOException{
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Managefood.fxml"));
        Stage stage = (Stage) foodButton.getScene().getWindow();
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent,800,600);
        scene.getStylesheets().add(getClass().getResource("/org/example/progastro/Managefood.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Managefood-ProGastro");
        stage.show();
    }
    private void switchToManageOrderController() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/progastro/Manageorder.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent,800,600);
        Stage stage = (Stage) orderButton.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("/org/example/progastro/Manageorder.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Manageorder-ProGastro");
        stage.show();
    }
    @FXML
    private void switchToLoginController(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/progastro/Login.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent,800,600);
        Stage stage = (Stage) orderButton.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("/org/example/progastro/Login.css").toExternalForm());
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
