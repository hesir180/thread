import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {
	// 等待在CountDownLatch上的线程需要10个线程全部完成，它才能执行
	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchDemo demo = new CountDownLatchDemo();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println("check complete");
			// 一个线程已经完成任务，倒计时计数器减一
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
		// 等待检查
		end.await();
		// 发射火箭
		System.out.println("Fire");
		exec.shutdown();
	}

}
