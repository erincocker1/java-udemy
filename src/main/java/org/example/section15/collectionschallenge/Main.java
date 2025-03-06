package org.example.section15.collectionschallenge;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        store.listProductsByCategory();
        Cart cart1 = store.newCart(false);
        Cart cart2 = store.newCart(true);
        store.printCarts();
        store.abandonCarts();
        store.printCarts();

        cart1.addItemByName("banana");
        cart1.addItemByName("apple", 5);
        cart1.removeItemByName("pear");
        cart1.checkout();
    }
}
