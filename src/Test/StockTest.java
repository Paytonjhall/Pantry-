package Test;

import Pantry.FoodItem;
import Pantry.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    FoodItem mockItem1 = new FoodItem("Eggs", 12);
    FoodItem mockItem2 = new FoodItem("Bread", 1);
    FoodItem mockItem3 = new FoodItem("Cheese", 2);

    Stock mockStock;

    @BeforeEach
    void setUp() {
        List<FoodItem> items = new ArrayList<>();
        items.add(mockItem1);
        items.add(mockItem2);
        mockStock = new Stock(items);
    }

    @Test
    void addItem() {
        Assertions.assertEquals(2, mockStock.getStock().size());
        mockStock.addItem(mockItem3);
        Assertions.assertEquals(3, mockStock.getStock().size());
        Assertions.assertTrue(mockStock.inStock(mockItem3));
    }

    @Test
    void removeItemThatExists() {
        Assertions.assertEquals(2, mockStock.getStock().size());
        mockStock.removeItem(mockItem1);
        Assertions.assertEquals(1, mockStock.getStock().size());
        Assertions.assertFalse(mockStock.inStock(mockItem1));
    }

    @Test
    void removeItemThatNotExists() {
        Assertions.assertEquals(2, mockStock.getStock().size());
        mockStock.removeItem(mockItem3);
        Assertions.assertEquals(2, mockStock.getStock().size());
    }

    @Test
    void inStock() {
        Assertions.assertTrue(mockStock.inStock(mockItem1));
        Assertions.assertTrue(mockStock.inStock(mockItem2));
        Assertions.assertFalse(mockStock.inStock(mockItem3));
    }
}