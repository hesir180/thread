import java.util.concurrent.atomic.AtomicInteger;

public class thread02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static class ThreadId {
		private static final AtomicInteger nextId = new AtomicInteger(0);
		private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
			@Override
			protected Integer initialValue() {
				// TODO Auto-generated method stub
				return nextId.getAndIncrement();
			}
			
		};
		
		
	}

}
