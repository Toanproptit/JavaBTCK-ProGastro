package model;

public class OrderItem {
    private Food food;
    private int quantity;

    public OrderItem(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Food getFood() { return food; }
    public int getQuantity() { return quantity; }
    public String getFoodName() {
        return food.getName(); // getter cho tên món ăn
    }

    public double getPrice() {
        return food.getPrice(); // getter cho giá món ăn
    }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
