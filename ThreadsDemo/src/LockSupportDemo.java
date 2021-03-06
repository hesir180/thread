import java.util.concurrent.locks.LockSupport;
/*
 * 线程阻塞工具，可以在任意位置让线程阻塞，不需要获得某个对象的锁，也不会抛出InterruptException
 * */
public class LockSupportDemo {
	public static Object u = new Object();
	static ChangeObjectThread t1=new ChangeObjectThread("t1");
	static ChangeObjectThread t2=new ChangeObjectThread("t2");
	public static class ChangeObjectThread extends Thread{
		public ChangeObjectThread(String name){
			super.setName(name);
		}
		public void run(){
			synchronized (u) {
				System.out.println("in"+getName());
				LockSupport.park();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		t1.start();
		Thread.sleep(100);
		t2.start();
		LockSupport.unpark(t1);
		LockSupport.unpark(t2);
		t1.join();
		t2.join();
	}

}
