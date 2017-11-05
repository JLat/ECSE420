package Assignment2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Filter implements Lock {
    int [] level;
    int [] victim;

    public Filter (int n){
        level = new int [n];
        victim = new int[n];
        for (int i = 1; 1 < n; i ++){
            level[i] = 0;
        }
    }

    public void lock (){
        int me = ThreadID.get();
        for (int i = 1; i < level.length; i ++){
            level[me] = i;
            victim[i] = me;

            for (int j = 0; j < level.length; j++)
                while(j != me && level[j] >= i && victim[i] == me);
        }
    }

    public void unlock(){
        int me = ThreadID.get();
        level[me] = 0;
    }


    //Empty Implementations
    public boolean tryLock(long l, TimeUnit t) {
        return false;
    }

    public boolean tryLock(){
        return false;
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public Condition newCondition(){
        return null;
    }
}
