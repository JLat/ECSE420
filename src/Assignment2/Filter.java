package Assignment2;

public class Filter implements Lock {
    int [] level;
    int [] victim;

    public Filter (int n){
        level = new int [n];
        victim = new int[n];
        for (int i = 0; i < n; i ++){
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

}
