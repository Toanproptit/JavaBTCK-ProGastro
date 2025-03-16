package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;


public class EditTableController {
    @FXML
    private Button addFood;

    @FXML
    private Button backButton;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private Button invoice;

    @FXML
    private TableColumn<OrderItem, String> colFoodName;

    @FXML
    private TableColumn<OrderItem, Double> colPrice;

    @FXML
    private TableColumn<OrderItem, Integer> colQuantity;

    @FXML
    private ComboBox<Food> comboMenu;

    @FXML
    private TableView<OrderItem> tableViewOrders;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTableName;

    @FXML
    private TextField txtTableStatus;

    @FXML
    private TextField quantityField;

    private Table table;
    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();;
    public void setTable(Table table) {
        this.table = table;
        txtTableName.setText(table.getName());
        txtTableStatus.setText(table.getStatus());
        orderItems = FXCollections.observableArrayList(table.getOrderItem());
        tableViewOrders.setItems(orderItems);
    }
    public void initialize() throws IOException {
        // Gán dữ liệu vào ComboBox (menu)
        comboMenu.getItems().addAll(FoodStorageJSON.loadFoods());

        // Gán các cột trong TableView
        colFoodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewOrders.setItems(orderItems);
    }


    @FXML
    public void handleAddItem(ActionEvent event) throws IOException {
        Food selectedFood = comboMenu.getSelectionModel().getSelectedItem();
        String quantityText = txtQuantity.getText();

        if (selectedFood != null && !quantityText.isEmpty()) {
            try {
                int quantity = Integer.parseInt(quantityText);

                if (quantity > 0) {

                    OrderItem newOrderItem = new OrderItem(selectedFood, quantity);
                    orderItems.add(newOrderItem);
                    table.setOrderFood(orderItems);
                    table.calculateTotalPrice();
                    TableJSON.updateTable(table);
                    tableViewOrders.setItems(orderItems);
                    showAlert("Thành công", "Đã thêm món ăn vào đơn hàng!");

                } else {
                    showAlert("Lỗi", "Số lượng phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                showAlert("Lỗi", "Vui lòng nhập số hợp lệ cho số lượng.");
            }
        } else {
            showAlert("Lỗi", "Vui lòng chọn món ăn và nhập số lượng.");
        }
    }

    @FXML
    void switchToManageOrder(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Manageorder.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(parent,800,600));
        stage.setTitle("ManageOrder - ProGastro");
        stage.show();
    }


    @FXML
    void handleDelete(ActionEvent event) throws IOException {
        TableJSON.earseTable(table);
        showAlert("Thông báo","Xóa Thành Công");
    }


    @FXML
    public void exportInvoiceToTXT(ActionEvent event) {
        try {
            File file = new File("Invoice.txt");
            FileWriter writer = new FileWriter(file);

            writer.write("Hóa Đơn\n");
            writer.write("====================================\n");

            // Duyệt qua các OrderItem trong TableView và viết thông tin vào file
            for (OrderItem orderItem : tableViewOrders.getItems()) {
                writer.write("Tên Món: " + orderItem.getFood().getName() + "\n");
                writer.write("Số Lượng: " + orderItem.getQuantity() + "\n");
                writer.write("Giá: " + orderItem.getFood().getPrice() + "\n");
                writer.write("====================================\n");
            }

            writer.write("Tổng Tiền: "+ table.getTotalPrice());
            writer.close();
            showAlert("Thông báo", "Hóa đơn đã được xuất thành công.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể xuất hóa đơn.");
        }
    }


    @FXML
    private void handleDeleteItem(ActionEvent event) {
        OrderItem orderItem = tableViewOrders.getSelectionModel().getSelectedItem();

        if(orderItem!=null){
            tableViewOrders.getItems().remove(orderItem);
            showAlert("Thông báo","Đã Xóa Món ăn");
        }
        else {
            showAlert("Lỗi","Vui Lòng chọn món ăn cần xóa");
        }
    }

    @FXML
    void handleEditItem(ActionEvent event) {

        OrderItem selectedOrderItem = tableViewOrders.getSelectionModel().getSelectedItem();

        if (selectedOrderItem != null) {
            try {
                int newQuantity = Integer.parseInt(quantityField.getText());
                if (newQuantity > 0) {
                    selectedOrderItem.setQuantity(newQuantity);
                    int index = orderItems.indexOf(selectedOrderItem);
                    if (index != -1) {
                        orderItems.set(index, selectedOrderItem);
                        table.setOrderFood(orderItems);
                        table.calculateTotalPrice();
                        tableViewOrders.refresh();
                        TableJSON.updateTable(table);
                        showAlert("Thông báo", "Số lượng món ăn đã được cập nhật.");
                    }
                } else {
                    showAlert("Lỗi", "Số lượng phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                showAlert("Lỗi", "Số lượng phải là một số hợp lệ.");
            }
        } else {
            showAlert("Lỗi", "Vui lòng chọn món ăn cần sửa.");
        }
    }


    @FXML
    void handleSave(ActionEvent event) {
        table.setName(txtTableName.getText());
        table.setStatus(txtTableStatus.getText());
        TableJSON.updateTable1(table);
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
