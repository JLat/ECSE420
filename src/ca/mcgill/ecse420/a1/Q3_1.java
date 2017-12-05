package ca.mcgill.ecse420.a1;

public class Q3_1 {
    public static void main (String [] args){

        philo [] philos = new philo[5];
        Object [] chopSticks = new Object[philos.length];

        for (int i = 0 ; i < chopSticks.length; i ++){
            chopSticks[i] = new Object();
        }

        //Basic Implementation


        for (int i = 0; i < philos.length; i ++) {
            philos[i] = new basicPhilo(chopSticks[i], chopSticks[(i + 1) % chopSticks.length]);
            Thread t = new Thread(philos[i]);
            t.start();
        }
    }


}


class philo implements  Runnable{
    protected Object leftStick, rightStick;

    public philo(){};

    public philo(Object leftStick, Object rightStick){
        this.leftStick = leftStick;
        this.rightStick = rightStick;
    }

    public void run(){

    }
}


 class basicPhilo extends philo implements Runnable{
     public basicPhilo(Object leftStick, Object rightStick){
         super(leftStick,rightStick);
     }

    @Override
    public void run(){
        while(true) {
            try{
                Thread.sleep((long)Math.random());
            }
            catch(Exception ex){}
            synchronized (leftStick) {
                System.out.println("Philo " + Thread.currentThread().getName() + " picked up his left chopstick");
                synchronized (rightStick) {
                    System.out.println("Philo " + Thread.currentThread().getName() + " picked up his right chopstick and is currently eating");
                }
                /*try{
                    Thread.sleep((long)Math.random());
                }
                catch(Exception ex){}*/
            }
        }
    }
}
