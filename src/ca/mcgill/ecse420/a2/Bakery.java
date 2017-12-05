package ca.mcgill.ecse420.a2;

public class Bakery implements Lock{
    boolean [] flag;
    int[] label;

    public Bakery (int n){
        flag  = new boolean[n];
        label = new int[n];
        for (int i = 0; i < n ; i ++){
            flag[i] = false;
            label[i] = 0;
        }
    }

    public void lock(){
        int i = ThreadID.get();
        flag[i] = true;
        label [i] = getMax(label);
        boolean spin = true;
        for (int j = 0 ; j < flag.length; j++)
            while(j != i && flag[j] && (label[j] < label[i]) || label[i] == label[j] && i > j);

    }

    public void unlock(){
        flag[ThreadID.get()] = false;
    }

    private int getMax(int [] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            max = arr[i] < max ? max: arr[i];
        }
        return max;
    }

}
