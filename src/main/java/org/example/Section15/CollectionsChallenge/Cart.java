package org.example.Section15.CollectionsChallenge;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static int idCounter = 1;
    private final int id;

    private final Store store;
    private Map<InventoryItem, Integer> products;
    private final LocalDate date;
    private final boolean virtual;

    Cart(Store store, boolean virtual) {
        this.id = idCounter++;
        this.store = store;
        this.products = new HashMap<>();
        this.date = LocalDate.now();
        this.virtual = virtual;

        store.carts.add(this);
    }

    public void addItem(InventoryItem item, int amount) {
        System.out.println();
        if (item.reserveItem(amount)) {
            products.merge(item, amount, Integer::sum);
            System.out.println("Added " + amount + " " + item.getProduct() + (amount > 1 ? "s" : "") + " to your cart");

        } else {
            System.out.println("Sorry, couldn't add " + amount + " " + item.getProduct() + (amount > 1 ? "s" : ""));
        }
    }

    public void addItemBySku(String sku, int amount) {
        if (store.inventory.containsKey(sku)) addItem(store.inventory.get(sku), amount);
        else System.out.println("\nSorry, couldn't find this item");
    }

    public void addItemBySku(String sku) {
        addItemBySku(sku, 1);
    }

    public void addItemByName(String name, int amount) {
        for (InventoryItem item : store.inventory.values()) {
            if (item.getName().equalsIgnoreCase(name)) {
                addItem(item, amount);
                return;
            }
        }
        System.out.println("\nSorry, couldn't find this item");
    }

    public void addItemByName(String name) {
        addItemByName(name, 1);
    }


    public void removeItem(InventoryItem item, int amount) {
        System.out.println();
        if (products.containsKey(item)) {
            if (products.get(item) >= amount) {
                products.merge(item, -amount, Integer::sum);
                System.out.println("Removed " + amount + " " + item.getProduct() + (amount > 1 ? "s" : "") + " from your cart");
            }
        }
        System.out.println("Sorry, couldn't remove " + amount + " " + item.getProduct() + (amount > 1 ? "s" : ""));
    }

    public void removeItemBySku(String sku, int amount) {
        if (store.inventory.containsKey(sku)) removeItem(store.inventory.get(sku), amount);
        else System.out.println("\nSorry, couldn't find this item");
    }

    public void removeItemBySku(String sku) {
        removeItemBySku(sku, 1);
    }

    public void removeItemByName(String name, int amount) {
        for (InventoryItem item : store.inventory.values()) {
            if (item.getName().equalsIgnoreCase(name)) {
                removeItem(item, amount);
                return;
            }
        }
        System.out.println("\nSorry, couldn't find this item");
    }

    public void removeItemByName(String name) {
        removeItemByName(name, 1);
    }

    public void printBasket() {
        System.out.println();
        System.out.println("-".repeat(30));
        System.out.println("Items in cart:");
        System.out.println("-".repeat(30));

        double total = 0;
        for (Map.Entry<InventoryItem, Integer> entry : products.entrySet()) {
            InventoryItem product = entry.getKey();
            int amount = entry.getValue();
            String productAndAmount = amount + " " + product.getName() + (amount > 1 ? "s" : "");
            System.out.printf("%s: £%.2f\n", productAndAmount, amount * product.getSalesPrice());
            total += amount * product.getSalesPrice();
        }

        System.out.printf("Total: £%.2f\n", total);
        System.out.println("-".repeat(30));
    }

    public void checkout() {
        products.forEach(InventoryItem::sellItem);
        System.out.println();
        System.out.println("Checking out:");
        printBasket();
        System.out.println("Thank you for shopping with Erin's Nonexistent Store.");
        store.carts.remove(this);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Cart " + id + " -- " + date + " -- " + (virtual ? "virtual" : "physical");
    }
}
