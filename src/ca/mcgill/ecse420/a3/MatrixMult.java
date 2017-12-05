package ca.mcgill.ecse420.a3;

public class MatrixMult {
    public static void main (String []args){
        double [][] a = new double [2][2];
        double [] b = new double [2];

        a[0][0] = 1;
        a[0][1] = 2;
        a[1][0] = 3;
        a[1][1] = 4;
        b[0] = 1;
        b[1] = 2;

        double [] out = sequentialMultiplyMatrix(a,b);

        for (int i = 0; i < out.length; i ++){
            System.out.print (" " + out[i] + " ");
        }


    }


    //From Assignment 1
    public static double [] sequentialMultiplyMatrix (double [][] a, double [] b ){
        double [] out = new double [a.length];
        for (int i = 0; i < a.length; i ++){
            for (int j = 0; j < b.length; j ++){
                out[i] += a[j][i] * b[j];
            }
        }
        return out;
    }

    public static double []
}


