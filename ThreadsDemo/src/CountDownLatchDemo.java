import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {
	// �ȴ���CountDownLatch�ϵ��߳���Ҫ10���߳�ȫ����ɣ�������ִ��
	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchDemo demo = new CountDownLatchDemo();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println("check complete");
			// һ���߳��Ѿ�������񣬵���ʱ��������һ
			end.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			exec.submit(demo);
		}
		// �ȴ����
		end.await();
		// ������
		System.out.println("Fire");
		exec.shutdown();
	}

}
