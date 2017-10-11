package Assignment1;

import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

public class Q1 {
    public static void main (String [] args) throws Exception{
        FileWriter fw = new FileWriter("out.csv");
        BufferedWriter bw = new BufferedWriter(fw);

        //Assume sqaure matrix

        bw.write("Varying Threads\n");
        bw.write("Threads, Size, Sequential, Parallel\n");
        for (int i = 1; i <5; i ++ ){
            execute(i,2000,fw, bw);
        }

        bw.write("Varying Size\n");
        bw.write("Threads, Size, Sequential, Parallel\n");

        for (int i = 500; i <2001; i +=250 ){
            execute(4,i,fw, bw);
        }

        bw.close();
        fw.close();
    }

    public static void execute (int threads, int n, FileWriter fw, BufferedWriter bw) throws  Exception{


        double a [][] = new double [n][n];
        double b [][] = new double [n][n];
        Random rand = new Random();

        for (int i =0; i < n ; i ++){
            for (int j = 0 ; j < n ; j ++){
                a [i][j] = rand.nextInt(100);
                b [i][j] = rand.nextInt(100);

            }
        }

        //printMatrix(a);
        //printMatrix(b);

        long time1 = System.nanoTime();
        double out [][] = sequentialMultiplyMatrix(a,b);
        long time2 = System.nanoTime();
        double out2 [][] = parallelMultiplyMatrix(a,b, threads);
        long time3 = System.nanoTime();



        if (out[n-1][n-1] == out2[n-1][n-1] && out[1][1] == out2[1][1]){
            System.out.println("IT WORKED");
            //printMatrix(out);
            //printMatrix(out2);
            bw.write(threads+"," + n +"," +  (time2-time1)/1000000 +"," +  (time3-time2)/1000000 + "\n");
        }
        else{
            bw.write(threads+"," + n +"," +  "ERROR"+ "\n");
        }

    }

    public static double [][] sequentialMultiplyMatrix (double [][] a, double [][] b ){
        double [][] out = new double [a.length][b[0].length];
        for (int i = 0; i < a.length; i ++){
            for (int j = 0; j < b[0].length; j ++){
                for (int k = 0; k < a[0].length; k ++)
                out[i][j] += a[i][k] * b[k][j];
            }
        }
        return out;
    }

    public static double [][] parallelMultiplyMatrix(double [][] a, double [][] b, int threads) throws Exception{
        double [][] out = new double [a.length][b[0].length];
        ExecutorService exec = Executors.newFixedThreadPool(threads);

        for (int i = 0 ; i < a.length; i ++){
            for (int j = 0; j < b[0].length; j ++){
                exec.execute(new multLine(a,b, out, i ,j));
            }
        }
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        return out;
    }


    public static void printMatrix(double [][] mat){
        for (int i = 0 ; i < mat.length; i ++){
            for (int j = 0; j < mat[0].length; j ++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }
}

 class multLine implements Runnable{
     private double [][] a, b, out;
     private int i,j;

    public multLine (double [][] a, double [][] b, double [][] out, int i, int j){
        this.a = a;
        this.b = b;
        this.out = out;
        this.i = i;
        this.j = j;
    }

    @Override
    public void run (){
        for (int k = 0 ; k < a[0].length; k ++){
            out[i][j] += a[i][k] * b[k][j];
        }
    }

}





