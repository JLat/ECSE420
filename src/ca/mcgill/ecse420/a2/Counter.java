package ca.mcgill.ecse420.a2;

public class Counter implements Runnable{
    private int value;
    private Lock lock;
    private int max;

    public Counter(int c, int m, Lock l) {
        value = c;
        lock = l;
        max = m;
    }

    public int getAndIncrement() {
        int temp;

        lock.lock();
        try{
            temp = value;
            value = temp + 1;
        } finally{
            lock.unlock();
        }
        System.out.println(temp);
        return temp;
    }

    public void run(){
        while(getAndIncrement() < max){
        }
    }
}


