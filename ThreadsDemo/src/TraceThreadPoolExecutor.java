import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/*
 * 解决线程池吞掉程序抛出的异常问题。
 * 一种简单方法，放弃submit，使用execute来提交；或者使用Future re=pools.submit(new DivTask(100,i)); re.get();来解决
 *以上两种方法都可以得到   部分   堆栈信息，仅仅是部分，我们只能知道异常是在哪里抛出的，但我们希望得到另外一些更重要的信息，那就是这个任务
 *到底在哪提交的？而任务的具体提交位置已经被线程池淹没了，通过扩展ThreadPoolExecutor让它在调度任务之前，先保存一下提交任务线程的堆栈信息。
 * */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor{
	
	public static class DivTask implements Runnable{
		int a,b;
		public DivTask(int a,int b){
			this.a=a;
			this.b=b;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			double re=a/b;
			System.out.println(re);
		}
		
	}
	public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(Runnable task) {
		// TODO Auto-generated method stub
		super.execute(wrap(task,clientTrace(),Thread.currentThread().getName()));
	}
	public Future<?> submit(Runnable task){
		return super.submit(wrap(task,clientTrace(),Thread.currentThread().getName()));
	}
	private Exception clientTrace(){
		return new Exception("Client stack trace");
	}
	private Runnable wrap(final Runnable task,final Exception clientStack,String clientThreadName){
		return new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					task.run();
				}catch(Exception e){
					clientStack.printStackTrace();
					throw e;
				}
			}
			
		};
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor pools =new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, 
				new SynchronousQueue<Runnable>());
		for(int i=0;i<5;i++){
			pools.execute(new DivTask(100, i));
		}
	}

}
