import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/*
 * ����̳߳��̵������׳����쳣���⡣
 * һ�ּ򵥷���������submit��ʹ��execute���ύ������ʹ��Future re=pools.submit(new DivTask(100,i)); re.get();�����
 *�������ַ��������Եõ�   ����   ��ջ��Ϣ�������ǲ��֣�����ֻ��֪���쳣���������׳��ģ�������ϣ���õ�����һЩ����Ҫ����Ϣ���Ǿ����������
 *���������ύ�ģ�������ľ����ύλ���Ѿ����̳߳���û�ˣ�ͨ����չThreadPoolExecutor�����ڵ�������֮ǰ���ȱ���һ���ύ�����̵߳Ķ�ջ��Ϣ��
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
