package org.example.section21.shoes;

import java.util.Random;
import java.util.stream.Stream;

public class Main {
    private static Random random = new Random();
    private static int id = 0;

    public static void main(String[] args) {
        ShoeWarehouse warehouse = new ShoeWarehouse();

        Thread producerThread = new Thread(() ->
                Stream.generate(Main::getRandomOrder)
                .limit(10)
                .forEach(warehouse::receiveOrder));

        Runnable consumerRunnable = () -> {for (int i = 0; i < 5; i++) warehouse.fulfillOrder();};
        Thread firstConsumerThread = new Thread(consumerRunnable);
        Thread secondConsumerThread = new Thread(consumerRunnable);

        producerThread.start();
        firstConsumerThread.start();
        secondConsumerThread.start();
    }

    private static Order getRandomOrder() {
        String shoeType = ShoeWarehouse.PRODUCTS[random.nextInt(ShoeWarehouse.PRODUCTS.length)];
        int quantity = random.nextInt(1, 5);
        return new Order(id++, shoeType, quantity);
    }



}
