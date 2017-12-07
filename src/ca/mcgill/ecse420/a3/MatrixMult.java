package ca.mcgill.ecse420.a3;

import java.util.Random;
import java.util.concurrent.*;

public class MatrixMult {
    public static void main (String []args) throws Exception{
        int n = 2000;
        int threads = 4;
        double a [][] = new double [n][n];
        double b [] = new double [n];
        Random rand = new Random();

        for (int i =0; i < n ; i ++){
            b [i] = rand.nextInt(100);
            for (int j = 0 ; j < n ; j ++){
                a [i][j] = rand.nextInt(100);
            }
        }

        long time1 = System.nanoTime();
        double [] out = sequentialMultiplyMatrix(a,b);
        long time2 = System.nanoTime();
        double [] out2 = parallelMultiplyMatrix(a,b,threads);
        long time3 = System.nanoTime();

        System.out.println("Sequentail: " + (time2-time1));
        System.out.println("Parallel: " + (time3-time2));
        System.out.println("Speed Up: " + (double)(time2-time1)/(time3-time2));
    }

    //From Assignment 1
    public static double [] sequentialMultiplyMatrix (double [][] a, double [] b ){
        double [] out = new double [a.length];
        for (int i = 0; i < a.length; i ++){
            for (int j = 0; j < b.length; j ++){
                out[i] += a[i][j] * b[j];
            }
        }
        return out;
    }

    public static double [] parallelMultiplyMatrix(double [][] a, double [] b, int threads) throws Exception{
        double [] out = new double [a.length];
        ExecutorService exec = Executors.newFixedThreadPool(threads);

        for (int i = 0 ; i < a.length; i ++){
            exec.execute(new vectorMult(a,b, out, i));
        }
        exec.shutdown();
        //exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        return out;
    }

    public static void printMatrix(double [] mat){
        for (int i = 0 ; i < mat.length; i ++){
            System.out.print(mat[i]+ " ");
        }
        System.out.print('\n');
    }

}

class vectorMult implements Runnable{
    private double [][] a;
    private double [] b, out;
    private int i,j;

    public vectorMult (double [][] a, double [] b, double [] out, int i){
        this.a = a;
        this.b = b;
        this.out = out;
        this.i = i;
    }

    @Override
    public void run (){
        out[i] = vecvec(a[i],b,0,a.length-1);
    }

    public double vecvec (double[] a, double b[], int l, int r){
        if (l==r)
            return a[l]*b[r];
        int m = (int)Math.floor((l+r)/2);
        double sumL = vecvec(a,b,l,m);
        double sumR = vecvec(a,b,m+1,r);
        return sumL + sumR;

    }

}


