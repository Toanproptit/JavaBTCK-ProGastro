package model;

import javafx.beans.binding.BooleanExpression;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private int index;
    private String name;
    private String status;
    private List<Food> orderFood;

    public Table(int index, String name, String status) {
        this.index = index;
        this.name = name;
        this.status = status;
        this.orderFood = new ArrayList<>();
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

    public List<Food> getOrderFood() {
        return orderFood;
    }
    public void setOrderFood(List<Food> orderFood) {
        this.orderFood = orderFood;
    }

}
