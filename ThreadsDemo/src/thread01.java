/*
 * 子线程循环10次,接着主线程循环100次,接着再回到子线程循环10次,再回到主线程循环100次,如此循环50次.
 * 
 * */
public class thread01 {
	public static void main(String[] args) {
		Thread t =Thread.currentThread();//首先获取当前线程对象
		final Business bs = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int j = 0; j < 50; j++) {
					bs.sub();
				}
			}
		}).start();

		for (int j = 0; j < 50; j++) {
			bs.main();
		}

	}
}

class Business {
	private boolean flag = true;

	public synchronized void sub() {
		while (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("sub thread" + i);
		}
		flag = false;
		this.notify();
	}

	public synchronized void main() {
		for (int i = 0; i < 10; i++) {
			System.out.println("main thread" + i);
		}
	}
}
