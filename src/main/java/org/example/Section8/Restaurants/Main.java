package org.example.Restaurants;
// Section 8 OOP Challenge
public class Main {
    public static void main(String[] args) {
        MealOrder mealOrder = new MealOrder();
        mealOrder.addTopping(new Topping("Cheese", 0.20));
        mealOrder.changeDrinkSize('L');
        mealOrder.printOrder();
        mealOrder.printTotalPrice();
    }
}
