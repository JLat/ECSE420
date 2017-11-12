package Assignment2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Test {
    public static void main (String [] args) throws InterruptedException{
        int nThreads = 4;
        Bakery b  = new Bakery(nThreads);
        //Filter f  = new Filter(nThreads);

        Thread[] pool = new Thread[nThreads];
        for(int i = 0; i < nThreads; i ++){
            pool[i] = new Thread (new Counter (0,100,b));
            //pool[i] = new Thread (new Counter(0, 100, f));
        }

        for (int i = 0; i < nThreads; i++) {
            pool[i].start();
        }

        for (int i = 0; i < nThreads; i++) {
            pool[i].join();
        }

    }
}
