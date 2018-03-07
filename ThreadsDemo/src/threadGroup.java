
public class threadGroup implements Runnable{
	public static void main(String []args) {
		ThreadGroup tg =new ThreadGroup("printGroup");
		Thread t1= new Thread(tg, new threadGroup(), "T1");
		t1.setPriority(Thread.MAX_PRIORITY);
		Thread t2 =new Thread(tg, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}, "T2");
		tg.activeGroupCount();
		tg.setDaemon(true);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
