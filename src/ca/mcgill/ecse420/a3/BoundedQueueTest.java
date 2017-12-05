package ca.mcgill.ecse420.a3;

public class BoundedQueueTest {
    public static void main (String []args) throws InterruptedException{
        BoundedQueue bq = new BoundedQueue(4);
        int nThreads = 2;

        Thread[] pool = new Thread[nThreads];

        for(int i = 0; i < nThreads; i ++){
            pool[i] = new Thread (new testQ(bq));
        }

        for (int i = 0; i < nThreads; i++) {
            pool[i].start();
        }

        for (int i = 0; i < nThreads; i++) {
            pool[i].join();
        }
    }

    public static class testQ implements Runnable {
        BoundedQueue bq;

        public testQ(BoundedQueue bq){
            this.bq = bq;
        }

        public void run() {
            try {
                bq.enq(new Integer(3));
                bq.enq(new Integer(4));
                bq.deq();
                bq.deq();
            }catch(Exception e){};
        }
    }



}
