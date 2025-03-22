package model;

import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private final String tableName;
    private final ObservableList<OrderItem> orderItems;
    private final double totalPrice;
    private final String timestamp;

    public Invoice(String tableName, ObservableList<OrderItem> orderItems, double totalPrice) {
        this.tableName = tableName;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getTableName() {
        return tableName;
    }

    public ObservableList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
