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
import model.Table;
import model.TableJSON;

import java.io.IOException;

public class ManageorderController {

    @FXML
    private Button backButton;

    @FXML
    private Button buttonAdd;

    @FXML
    private TableColumn<Table, Double> columnId;

    @FXML
    private TableColumn<Table, String> columnName;

    @FXML
    private TableColumn<Table, String> columnStatus;

    @FXML
    private TableView<Table> tableview;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    private ObservableList<Table> observableList = FXCollections.observableArrayList();
    @FXML
    public void initialize()throws IOException{
        observableList = FXCollections.observableArrayList(TableJSON.loadTable());
        columnId.setCellValueFactory(new PropertyValueFactory<>("index"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableview.setItems(observableList);
//        tableview.setOnMouseClicked(this::handleTableViewClick);
    }
    @FXML
    private void switchToDashBoard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/org/example/progastro/Dashboard.fxml"));
        Parent dashboardview = fxmlLoader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(dashboardview,800,600));
        stage.setTitle("Dashboard - ProGastro");
        stage.show();
    }
    @FXML
    public void handleAddTable(ActionEvent event)throws IOException{
        int index = Integer.parseInt(textField1.getText());
        String name = textField2.getText();
        String status = textField3.getText();
        Table newTable = new Table(index,name,status);
        TableJSON.addTable(newTable);
        observableList = FXCollections.observableArrayList(TableJSON.loadTable());
        tableview.setItems(observableList);
        showAlert("Thông báo","Đã Thêm Bàn");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}