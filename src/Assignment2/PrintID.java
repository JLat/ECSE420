package Assignment2;

public class PrintID implements Runnable{
	
	@Override
	public void run() {
		int id  = ThreadID.get();
		
		for (int i = 0; i < 10; i++) {
			int tempid  = ThreadID.get();
			
			System.out.println("ID: " + id + " temp: " + tempid);
		}
		
		
	}
	
	

}
