import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockWithCondition implements Runnable{
	/*
	 * Condition”ÎObject¿‡À∆
	 * 
	 * */
	public static ReentrantLock lock= new ReentrantLock();
	public static Condition condition =lock.newCondition();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			lock.lock();
			condition.await();
			System.out.println("Thread is going on");
		}catch(InterruptedException e){
			
		}finally{
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ReenterLockWithCondition t1=new ReenterLockWithCondition();
		Thread t =new Thread(t1);
		t.start();
		Thread.sleep(2000);
		lock.lock();
		condition.signal();
		lock.unlock();
		
	}
}
