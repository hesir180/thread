/*
 * ���߳�ѭ��10��,�������߳�ѭ��100��,�����ٻص����߳�ѭ��10��,�ٻص����߳�ѭ��100��,���ѭ��50��.
 * 
 * */
public class thread01 {
	public static void main(String[] args) {
		Thread t =Thread.currentThread();//���Ȼ�ȡ��ǰ�̶߳���
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
