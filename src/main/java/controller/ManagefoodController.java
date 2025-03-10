package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Food;
import model.FoodStorageJSON;
import java.io.IOException;




public class ManagefoodController {

    @FXML
    private Button addfood;

    @FXML
    private Button backButton;

    @FXML
    private TextField data1AddFood;

    @FXML
    private TextField data2AddFood;

    @FXML
    private TextField data3AddFood;

    @FXML
    private TextField dataAddFood1;

    @FXML
    private TextField dataErasefood1;

    @FXML
    private TextField dataFixFood1;

    @FXML
    private Button erasefood;

    @FXML
    private Button fixfood;

    FoodStorageJSON foodStorageJSON =new FoodStorageJSON();

    public void handle(ActionEvent event)throws IOException{
        switchToDashBoard();
    }
    public void handleAddFood() throws IOException {
        String name = data1AddFood.getText();
        String description = data2AddFood.getText();
        double price;

        try {
            price = Double.parseDouble(data3AddFood.getText());
        } catch (NumberFormatException e) {
            showAlert("Lỗi", "Giá phải là số");
            return;
        }

        if (name.isEmpty() || description.isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin món ăn");
            return;
        }

        Food newFood = new Food(name, description, price);
        FoodStorageJSON.addFood(newFood);

//        foodStorageJSON.getFoodList()= FXCollections.observableArrayList(FoodStorageJSON.loadFoods());

        showAlert("Thành Công", "Đã thêm món ăn thành công");
    }

    private void switchToDashBoard() throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Dashboard.fxml"));
        Parent dashboardview = fxmlLoader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
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
