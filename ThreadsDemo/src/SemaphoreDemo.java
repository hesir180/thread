import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable{
	final Semaphore semp=new Semaphore(5);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SemaphoreDemo  sd=new SemaphoreDemo();
		for(int i=0;i<20;i++){
			exec.submit(sd);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			semp.acquire();
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId()+":done");
			semp.release();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
