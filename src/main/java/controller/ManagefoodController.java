package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Food;
import model.FoodStorageJSON;

import javafx.scene.input.MouseEvent;

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
    private TableColumn<Food,String> descriptionColumn;

    @FXML
    private Button erasefood;

    @FXML
    private Button fixfood;

    @FXML
    private TableColumn<Food, String> nameColumn;

    @FXML
    private TableView<Food> foodTable;

    @FXML
    private TableColumn<Food, Double> priceColumn;
    private ObservableList<Food> foods = FXCollections.observableArrayList();
    FoodStorageJSON foodStorageJSON =new FoodStorageJSON();
    public void initialize()throws IOException{
        foods = FXCollections.observableArrayList(FoodStorageJSON.loadFoods());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        foodTable.setItems(foods);
        foodTable.setOnMouseClicked(this::handleTableViewClick);
    }
    public void handle(ActionEvent event)throws IOException{
        switchToDashBoard();
    }
    @FXML
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
        foods = FXCollections.observableArrayList(FoodStorageJSON.loadFoods());
        foodTable.setItems(foods);
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

    private void handleTableViewClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            int rowIndex = foodTable.getSelectionModel().getSelectedIndex();
            Food selectedFood = foodTable.getSelectionModel().getSelectedItem();
            if (selectedFood != null) {
                openEditFood(selectedFood);
            }
        }
    }
    private void openEditFood(Food selectedFood) {
        try {
            FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/EditFood.fxml"));
            Parent parent = fxmlLoader.load();
            EditFoodController controller =fxmlLoader.getController();
            controller.setFood(selectedFood);
            Stage stage = (Stage) foodTable.getScene().getWindow();
            stage.setScene(new Scene(parent,800,600));
            stage.setTitle("Chỉnh sửa món ăn");
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể mở màn hình chỉnh sửa món ăn!");
        }
    }
}
