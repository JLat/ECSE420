package ca.mcgill.ecse420.a1;

public class Q2 {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main (String [] args){
        Thread t1 = new Thread( new t1());
        Thread t2 = new Thread( new t2());
        t1.start();
        t2.start();
    }

    public static class t1 implements Runnable{
        public void run(){
            while(true) {
                synchronized (lock1) {
                    synchronized (lock2) {
                        System.out.println("t1");
                    }
                }
            }
        }
    }

    public static class t2 implements Runnable{
        public void run(){
            while(true) {
                synchronized (lock2) {
                    synchronized (lock1) {
                        System.out.println("t2");
                    }
                }
            }
        }
    }
}
