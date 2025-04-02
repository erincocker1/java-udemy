package org.example.section21.shoes;

import java.util.LinkedList;

public class ShoeWarehouse {

    public static final String[] PRODUCTS = {
            "Trainers", "Sandals", "Heels", "Boots", "Platforms", "Flip Flops", "Flats"};

    private LinkedList<Order> orders = new LinkedList<>();

    public synchronized void receiveOrder(Order order) {
        while (orders.size() >= 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        orders.add(order);
        System.out.println(Thread.currentThread().getName() + " received order: " + order);
        notifyAll();

    }

    public synchronized Order fulfillOrder() {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order order = orders.remove();
        System.out.println(Thread.currentThread().getName() + " fulfilled order: " + order);
        notifyAll();
        return order;
    }
}
