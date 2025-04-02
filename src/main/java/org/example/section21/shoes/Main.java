package org.example.section21.shoes;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {
    private static Random random = new Random();
    private static int id = 0;

    public static void main(String[] args) {
//        oldMain();
        newMain(15, 3);
    }

    public static void newMain(int orders, int consumerThreads) {
        ShoeWarehouse warehouse = new ShoeWarehouse();

        ExecutorService producerExecutor = Executors.newSingleThreadExecutor();
        producerExecutor.execute(() ->
                Stream.generate(Main::getRandomOrder)
                        .limit(orders)
                        .forEach(warehouse::receiveOrder));


        Callable<Order> consumerCallable = warehouse::fulfillOrder;
        ExecutorService consumerExecutor = Executors.newFixedThreadPool(consumerThreads);
        try {
            consumerExecutor.invokeAll(Collections.nCopies(orders, consumerCallable));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        producerExecutor.shutdown();
        consumerExecutor.shutdown();
    }

    public static void oldMain() {
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
