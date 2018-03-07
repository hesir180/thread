import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadPool {
	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		for (int j = 0; j < 10; j++) {
			final int task = j;
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 10; i++) {
						System.out.println(Thread.currentThread().getName() + "is loop" + i+"for task"+task);
					}
				}

			});
		}
	}
}
