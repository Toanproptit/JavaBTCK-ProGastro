package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Food;
import model.FoodStorageJSON;
import java.io.IOException;

public class EditFoodController {
    @FXML
    private Button back;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private Button sign;

    @FXML
    private Button eraseButton;

    @FXML
    public void handleSign(ActionEvent event)throws IOException{
        saveFood();
    }

    public void handleErase(ActionEvent event)throws IOException{
        eraseFood();
    }

    @FXML
    private void switchToManagefood() throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Managefood.fxml"));
        Parent dashboard = fxmlLoader.load();
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(dashboard,800,600));
        stage.setTitle("ManageFood - ProGastro");
        stage.show();
    }
    private Food food;

    public void setFood(Food food) {
        this.food = food;
        nameField.setText(food.getName());
        descriptionField.setText(food.getDescription());
        priceField.setText(String.valueOf(food.getPrice()));
    }

    public void eraseFood() throws IOException{
        FoodStorageJSON.eraseFood(food);
        showAlert("Thông báo","Xóa Thành Công");
    }
    public void saveFood() throws IOException {
        food.setName(nameField.getText());
        food.setDescription(descriptionField.getText());
        food.setPrice(Double.parseDouble(priceField.getText()));
        FoodStorageJSON.updateFood(food);
        showAlert("Lưu thành công","success");

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
