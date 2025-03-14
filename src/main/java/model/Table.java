package model;

import javafx.beans.binding.BooleanExpression;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private int index;
    private String name;
    private String status;
    private List<OrderItem> orderItems;
    private int id;
    public Table(int index, String name, String status) {
        this.index = index;
        this.name = name;
        this.status = status;
        this.orderItems = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItem() {
        return orderItems;
    }
    public void setOrderFood(List<OrderItem> orderFood) {
        this.orderItems = orderFood;
    }

}
