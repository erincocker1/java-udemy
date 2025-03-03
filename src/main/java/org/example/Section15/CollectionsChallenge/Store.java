package org.example.Section15.CollectionsChallenge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    Map<String, InventoryItem> inventory = new HashMap<>(); //keyed by sku
    List<Cart> carts = new ArrayList<>();
    Map<Category, Map<String, InventoryItem>> aisleInventory = new HashMap<>(); //keyed by category

    public Store() {
        stockInventory();
        stockAisleInventory();
    }

    private void stockInventory() {
        List<Product> products = new ArrayList<>(List.of(
                new Product("A100","apple","local", Category.PRODUCE),
                new Product("B100","banana","local",Category.PRODUCE),
                new Product("P100","pear","local",Category.PRODUCE),
                new Product("L103","lemon","local",Category.PRODUCE),
                new Product("M201","milk","farm",Category.DAIRY),
                new Product("Y001","yogurt","farm",Category.DAIRY),
                new Product("C333","cheese","farm",Category.DAIRY),
                new Product("R777","rice chex","Nabisco",Category.CEREAL),
                new Product("G111","granola","Nat Valley",Category.CEREAL),
                new Product("BB11","ground beef","butcher",Category.MEAT),
                new Product("CC11","chicken","butcher",Category.MEAT),
                new Product("BC11","bacon","butcher",Category.MEAT),
                new Product("BC77","coke","coca cola",Category.BEVERAGE),
                new Product("BC88","coffee","value",Category.BEVERAGE),
                new Product("BC99","tea","herbal",Category.BEVERAGE)
        ));
        products.forEach(x -> inventory.put(x.sku(),
                new InventoryItem(x, 10, 0, 10, 2, 5)));
    }

    private void stockAisleInventory() {
        for (InventoryItem item : inventory.values()) {
            Category category = item.getCategory();
            if (!aisleInventory.containsKey(category)) {
                aisleInventory.put(category, new HashMap<>());
            }
            aisleInventory.get(category).put(item.getSku(), item);
        }
    }

    public void listProductsByCategory() {
        System.out.println();
        System.out.println("Products in the store:");
        System.out.println("-".repeat(30));
        for (Category category : aisleInventory.keySet()) {
            System.out.println(category + ":");
            for (InventoryItem item : aisleInventory.get(category).values()) {
                System.out.println(item);
            }
            System.out.println("-".repeat(30));
        }
    }

    public Cart newCart(boolean isVirtual) {
        return new Cart(this, isVirtual);
    }

    public void printCarts() {
        System.out.println();
        System.out.println("Carts at the store:");
        carts.forEach(System.out::println);
    }

    public void abandonCarts() {
        System.out.println();
        System.out.println("Abandoning old carts...");

        int firstActiveCart = -1;
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getDate().equals(LocalDate.now())) {
                firstActiveCart = i;
                break;
            }
        }
        if (firstActiveCart == -1) {
            carts.clear();
        } else {
            carts = carts.subList(firstActiveCart, carts.size());
        }
    }
}
