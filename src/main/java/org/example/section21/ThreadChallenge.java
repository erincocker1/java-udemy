package org.example.section21;

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.print(i * 2 + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(this.getName() + " has been interrupted.");
                break;
            }
        }
    }
}

public class ThreadChallenge {
    public static void main(String[] args) {
        Thread firstThread = new MyThread("firstThread");


        Thread secondThread = new Thread(() -> {
            try {
                Thread.sleep(500);
                for (int i = 0; i < 5; i++) {
                    System.out.print(i * 2 + 1 + " ");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("secondThread has been interrupted.");
            }
        }, "secondThread");

        firstThread.start();
        secondThread.start();

        try {
            Thread.sleep(3000);
            firstThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
