package org.example;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
/**Класс "Philosopher" представляет философа и его поведение*/
public class Philosopher extends Thread {
    private final String name;
    private final Fork left;
    private final Fork right;
    private int countEat;
    private final Random random;
    CountDownLatch cdl; //используется для синхронизации потоков

    public Philosopher(String name, Fork left, Fork right, CountDownLatch cdl) {
        this.name = name;
        this.left = left;
        this.right = right;
        this.cdl = cdl;
        countEat = 0;
        random = new Random();
    }
/**Метод "run()" является основным методом работы с потоком. Внутри этого метода философ ждет случайное время, чтобы симулировать процесс размышления.
 * Затем он пытается есть, проверяя, свободны ли его левая и правая вилки. Если обе вилки свободны, философ начинает есть, вызывая метод "eating()".*/
    @Override
    public void run() {
        try {
            sleep(random.nextLong(100, 2000));
            while (countEat < 3) { //ест три раза
                if (!left.isUsing() && !right.isUsing()) {
                    eating();
                    countEat++;
                } else {
                    sleep(random.nextLong(1000, 3000));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " больше не голоден");
        cdl.countDown();
    }
    /**Метод еды*/
    private void eating() throws InterruptedException {
        changeForkOptions();
        System.out.println(name + " начинает есть спагетти вилками: " + left
                + " и " + right);
        sleep(random.nextLong(3000, 6000));
        changeForkOptions();
        System.out.println(name + " закончил есть, размышляет. " +
                "Положил на место вилки " + left + " и " + right);
        sleep(random.nextLong(3000, 6000));
    }

    private synchronized void changeForkOptions() {
        left.setUsing(!left.isUsing());
        right.setUsing(!right.isUsing());
    }
}