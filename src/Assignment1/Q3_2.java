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

class leftyPhilo extends philo implements Runnable {
    public leftyPhilo(Object leftStick, Object rightStick){
        super(leftStick,rightStick);
    }

    @Override
    public void run(){
        while(true) {
            try{
                Thread.sleep((long)Math.random());
            }
            catch(Exception ex){}
            synchronized (rightStick) {
                System.out.println("Philo " + Thread.currentThread().getName() + " picked up his right chopstick");
                synchronized (leftStick) {
                    System.out.println("Philo " + Thread.currentThread().getName() + " picked up his left chopstick and is currently eating");
                }
                /*try{
                    Thread.sleep((long)Math.random());
                }
                catch(Exception ex){}*/
            }
        }
    }

}
