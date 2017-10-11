package Assignment1;

public class Q3_2 {
    public static void main (String []args){

        philo [] philos = new philo[5];
        Object [] chopSticks = new Object[philos.length];

        for (int i = 0 ; i < chopSticks.length; i ++){
           chopSticks[i] = new Object();
        }

        //Lefty Implementation to avoid deadlock


        for (int i = 1; i < philos.length; i ++){
            if (i == 0){
                 philos[i]= new leftyPhilo(chopSticks[0], chopSticks[(0+1)% chopSticks.length]);
            }
            else{
                philos[i] = new basicPhilo(chopSticks[i], chopSticks[(i+1)% chopSticks.length]);
            }
            Thread t = new Thread(philos[i]);
            t.start();
        }

    }
}
