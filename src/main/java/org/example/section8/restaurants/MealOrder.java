package org.example.Restaurants;

import java.util.ArrayList;

public class MealOrder {
    private Burger burger;
    private Drink drink;
    private Side side;

    public MealOrder() {
        this(new Burger(), new Drink(), new Side());
    }

    public MealOrder(Burger burger, Drink drink, Side side) {
        this.burger = burger;
        this.drink = drink;
        this.side = side;
    }

    public void addTopping(Topping topping) {
        this.burger.addTopping(topping);
    }

    public void changeDrinkSize(char size) {
        this.drink.changeSize(size);
    }

    public void printTotalPrice() {
        System.out.println("Total Price: £" + (this.burger.getTotalPrice() + this.drink.getTotalPrice() + this.side.getPrice()));
    }

    public void printOrder() {
        System.out.println("Your Order: ");
        System.out.println(this.burger);
        System.out.println(this.side);
        System.out.println(this.drink);
    }
}

class Burger {
    private String type;
    private double basePrice;
    private ArrayList<Topping> toppings;
    private boolean isDeluxe;

    Burger() {
        this("Hamburger", 2.99);
    }

    Burger(String type, double basePrice) {
        this.type = type;
        this.basePrice = basePrice;
        this.toppings = new ArrayList<Topping>();
        this.isDeluxe = false;
    }

    double getTotalPrice() {
        if (this.isDeluxe) {
            return 7.99;
        }
        double totalPrice = 0;
        for (Topping topping : this.toppings) {
            totalPrice += topping.getPrice();
        }
        return totalPrice + this.basePrice;
    }

    void addTopping(Topping topping) {
        if (this.toppings.size() < 3 || (this.toppings.size() < 5 && this.isDeluxe)) {
            this.toppings.add(topping);
            System.out.println(topping.getType() + " added");
        } else {
            System.out.println("Can't add any more toppings!");
        }
    }

    void makeDeluxe() {
        this.isDeluxe = true;
        System.out.println("Burger is now deluxe!");
    }

    @Override
    public String toString() {
        StringBuilder toppings = new StringBuilder();
        for (Topping topping : this.toppings) {
            toppings.append(topping.getType()).append(", ");
        }
        toppings.delete(toppings.length() - 2, toppings.length() - 1);
        return this.type + " with " + toppings.toString();
    }
}

class Topping {
    private String type;
    private double price;

    public Topping(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getType() {
        return type;
    }
}

class Drink {
    private String type;
    private double basePrice;
    private char size;

    Drink() {
        this("Water", 0.99, 'R');
    }

    Drink(String type, double basePrice, char size) {
        this.type = type;
        this.basePrice = basePrice;
        if (size == 'S' || size == 'R' || size == 'L') {
            this.size = size;
        } else {
            this.size = 'R';
        }
    }

    double getTotalPrice() {
        return switch (this.size) {
            case 'S' -> this.basePrice * 0.75;
            case 'L' -> this.basePrice * 1.25;
            default -> this.basePrice;
        };
    }

    void changeSize(char size) {
        if (size == 'S' || size == 'R' || size == 'L') {
            this.size = size;
        } else {
            this.size = 'R';
        }
        System.out.println("Size changed to " + size);
    }

    @Override
    public String toString() {
        return this.type;
    }
}

class Side {
    private String type;
    private double price;

    Side() {
        this("Fries", 1.49);
    }

    Side(String type, double price) {
        this.type = type;
        this.price = price;
    }

    double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
