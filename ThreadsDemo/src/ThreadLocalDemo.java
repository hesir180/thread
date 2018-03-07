import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalDemo {

	private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	static class MyThread implements Runnable {
		private int index;

		public MyThread(int index) {
			this.index = index;
		}

		public void run() {
			System.out.println("�߳�" + index + "�ĳ�ʼvalue:" + value.get());
			for (int i = 0; i < 10; i++) {
				value.set(value.get() + i);
			}
			System.out.println("�߳�" + index + "���ۼ�value:" + value.get());
		}
	}

	static class YourThread implements Runnable {
		private int index;

		public YourThread(int index) {
			this.index = index;
		}

		public void run() {
			System.out.println("�߳�" + index + "�ĳ�ʼvalue:" + value.get());
			for (int i = 10; i < 20; i++) {
				value.set(value.get() + i);
			}
			System.out.println("�߳�" + index + "���ۼ�value:" + value.get());
		}
	}

	// =================================================================================//
	/*
	 * ���߳��²��������
	 */
	// ÿ���߳�Ҫ���������������
	public static final int GEN_COUNT = 10000000;
	// ���빤�����߳���
	public static final int THREAD_COUNT = 4;
	// �����̳߳�
	static ExecutorService exe = Executors.newFixedThreadPool(THREAD_COUNT);
	// ���屻����̹߳����Randomʵ�����ڲ��������
	public static Random rnd = new Random(123);
	// ����ThreadLocal��װ��Random
	public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>() {
		@Override
		protected Random initialValue() {
			return new Random(123);
		}
	};

	public static class RndTask implements Callable<Long> {
		private int mode = 0;

		public RndTask(int mode) {
			this.mode = mode;
		}

		public Random getRandom() {
			if (mode == 0) {
				return rnd;
			} else if (mode == 1) {
				return tRnd.get();
			} else {
				return null;
			}
		}

		@Override
		public Long call() throws Exception {
			// TODO Auto-generated method stub
			long b = System.currentTimeMillis();
			for (long i = 0; i < GEN_COUNT; i++) {
				getRandom().nextInt();
			}
			long e = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + "spend" + (e - b) + "ms");
			return e - b;
		}

	}

	// main
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		for (int i = 0; i < 5; i++) {
			new Thread(new MyThread(i)).start();
		}
		for (int i = 5; i < 10; i++) {
			new Thread(new YourThread(i)).start();
		}
		// =====================================================//
		Future<Long>[] futs = new Future[THREAD_COUNT];
		for (int i = 0; i < THREAD_COUNT; i++) {
			futs[i] = exe.submit(new RndTask(0));
		}
		long totaltime = 0;
		for (int i = 0; i < THREAD_COUNT; i++) {
			totaltime += futs[i].get();
		}
		System.out.println("���̷߳���ͬһ��Randomʵ��:" + totaltime + "ms");

		// ThreadLocal�����
		for (int i = 0; i < THREAD_COUNT; i++) {
			futs[i] = exe.submit(new RndTask(1));
		}
		totaltime = 0;
		for (int i = 0; i < THREAD_COUNT; i++) {
			totaltime += futs[i].get();
		}
		System.out.println("ʹ��ThreadLocal��װRandomʵ��:" + totaltime + "ms");
		exe.shutdown();
	}

}
