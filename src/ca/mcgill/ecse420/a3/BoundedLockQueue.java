package ca.mcgill.ecse420.a3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedLockQueue<T> {
    T[] items;
    int head = 0;
    int tail = 0;

    Lock headLock = new ReentrantLock();
    Lock tailLock = new ReentrantLock();

    Condition notFull = tailLock.newCondition();
    Condition notEmpty = headLock.newCondition();

    public BoundedLockQueue( int size){
        items = (T[]) new Object[size];
    }

    public void enqueue (T item){
        tailLock.lock();
        try{
            while(tail - head == items.length){
                try {
                    notFull.await();
                }
                catch(Exception e){};
            }
            items[ tail % items.length] = item;

            tail++;

            if (tail - head == 1)
                notEmpty.signal();
        }finally {
            tailLock.unlock();
        }
    }

    public T dequeue (T item){
        headLock.lock();
        try {
            while (tail - head == 0){
                try{ notEmpty.await();}
                catch(Exception e){}
            }

            T temp = items[head % items.length];
            head ++;

            if (tail - head == items.length -1)
                notFull.signal();

            return temp;
        }finally {
            headLock.unlock();
        }
    }
}
