package org.example;

import java.util.concurrent.CountDownLatch;
/**Класс "PhilosophersTable" представляет стол, за которым сидят философы.*/
public class PhilosophersTable extends Thread {
    private final int PHILOSOPHER_COUNT = 5; // количество философов
    Fork[] forks;
    Philosopher[] philosophers;
    CountDownLatch cdl;


    public PhilosophersTable() {
        this.forks = new Fork[PHILOSOPHER_COUNT + 2];
        this.philosophers = new Philosopher[PHILOSOPHER_COUNT + 1];
        cdl = new CountDownLatch(PHILOSOPHER_COUNT);
        init();
    }
/**Метод "run()" запускает процесс размышления философов и вызывает метод "await()" счетчика CountDownLatch
 * для ожидания завершения всех потоков философов.*/
    @Override
    public void run() {
        System.out.println("За столом сидят умные и голодные философы");
        try {
            think();
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Философы больше не голодные. Они только размышляют");
    }

    private void init() {
        for (int i = 1; i < PHILOSOPHER_COUNT + 1; i++) {
            forks[i] = new Fork(i);
        }
        forks[6] = forks[1];

        for (int i = 1; i < PHILOSOPHER_COUNT + 1; i++) {
            philosophers[i] = new Philosopher("Философ №" + i, forks[i], forks[i + 1], cdl);
        }
    }

    private void think() {
        for (int i = 1; i < PHILOSOPHER_COUNT + 1; i++) {
            philosophers[i].start();
        }
    }
}