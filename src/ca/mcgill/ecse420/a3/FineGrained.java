package ca.mcgill.ecse420.a3;

public class FineGrained<T> {
    Node head, tail;

    public FineGrained(){
        head = new Node((T)new Object());
        tail = new Node((T)new Object());
        head.next = tail;
    }

    public  boolean add(T item){
        int key = item.hashCode();
        head.lock();
        Node pred = head;
        try{
            Node curr = pred.next;
            curr.lock();
            try{
                while(curr.key < key){
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                if (curr.key == key){
                    return false;
                }
                Node newNode = new Node(item);
                newNode.next = curr;
                pred.next = newNode;
                return true;
            }finally{
                curr.unlock();
            }
        }finally{
            pred.unlock();
        }
    }

    public boolean remove (T item){
        Node pred = null, curr = null;
        int key = item.hashCode();
        head.lock();
        try{
            pred = head;
            curr = pred.next;
            curr.lock();
            try{
                while(curr.key < key){
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                if (curr.key == key){
                    pred.next = curr.next;
                    return true;
                }
                return false;
            } finally{
                curr.unlock();
            }
        } finally {
            pred.unlock();
        }
    }

    public boolean contain (T item){
        Node pred = null, curr = null;
        int key = item.hashCode();
        head.lock();
        try{
            pred = head;
            curr = pred.next;
            curr.lock();
            try{
                while(curr.key < key){
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                if (curr.key == key){
                    return true;
                }
                return false;
            } finally{
                curr.unlock();
            }
        } finally {
            pred.unlock();
        }
    }
}