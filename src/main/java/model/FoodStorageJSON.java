package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FoodStorageJSON {
    private static final String FILE_PATH = "resources/data/food.json";
    public static List<Food> foodList = new ArrayList<>();

    public static List<Food> loadFoods() throws IOException {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            foodList = new Gson().fromJson(reader, new TypeToken<List<Food>>() {}.getType());
            if (foodList == null) {
                foodList = new ArrayList<>();
            }
        } catch (IOException e) {
            foodList = new ArrayList<>();
        }
        return foodList;
    }
    public static void saveFoods() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileWriter writer = new FileWriter(file)) {
            new Gson().toJson(foodList, writer);
        }
    }
    public static void addFood(Food food) {
        foodList.add(food);
        try {
            saveFoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
