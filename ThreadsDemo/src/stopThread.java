
public class stopThread {
	public static class ChangeObjectThread extends Thread {
		volatile boolean stopme=false;//关键字volatile是轻量级的线程同步
		public void stopMe(){
			stopme=true;
		}
		@Override
		public void run() {
			
			while (true){
				if(stopme){
					System.out.println("exit by stop me");
					break;
				}
			}
			synchronized (stopThread.class) {
				
			}
			Thread.yield();
		}
	}
}
