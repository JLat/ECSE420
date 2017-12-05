package ca.mcgill.ecse420.a1;

import java.util.concurrent.Semaphore;

public class Q3_3 {

   public static void main(String[] args) {
        Semaphore waiter = new Semaphore(1);
        int n = 10;

        Chopstick[] chopsticks = new Chopstick[n];
        for (int i = 0; i < n; i++) {
            chopsticks[i] = new Chopstick();
        }

        Philosopher[] philosophers = new Philosopher[n];
        for (int i = 0; i < n; i++) {
            philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i+1)%n], waiter);
            Thread t = new Thread(philosophers[i]);
            t.start();
        }
   }

}

class Chopstick {

    public boolean inUse;

    public Chopstick(){
        inUse = false;
    }
}

class Philosopher implements Runnable {

    private Chopstick c1;
    private Chopstick c2;
    private Semaphore waiter;

    public Philosopher (Chopstick c1, Chopstick c2, Semaphore waiter) {
        this.c1 = c1;
        this.c2 = c2;
        this.waiter = waiter;
    }

    public void run () {
        while (true) {
            try {
                Thread.sleep(((long) Math.random() + 1) * 100);

                waiter.acquire();
                while(c1.inUse || c2.inUse) {

                }
                c1.inUse = true;
                c2.inUse = true;
                System.out.println("Philo " + Thread.currentThread().getName() + " is currently eating");
                //Thread.sleep(((long) Math.random() + 1) * 100);
                waiter.release();

                c1.inUse = false;
                c2.inUse = false;

            }
            catch (Exception e){}
        }
    }

}
