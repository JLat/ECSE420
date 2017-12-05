package ca.mcgill.ecse420.a3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node<T> {
    T item;
    int key;
    Node next;
    Lock lck = new ReentrantLock();

    public Node (T item){
        this.item = item;
        key = item.hashCode();
        next = null;
    }

    public void lock (){
        lck.lock();
    }

    public void unlock (){
        lck.unlock();
    }

}


