import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
/*
 * 分而治之 Fork/Join
 * 
 * */
public class ForkJoinCountDemo extends RecursiveTask<Long>{
	private static final int THRESHOLD = 1000;
	private long start ;
	private long end;
	public ForkJoinCountDemo(long start ,long end) {
		// TODO Auto-generated constructor stub
		this.start=start;
		this.end=end;
	}

	@Override
	protected Long compute() {
		// TODO Auto-generated method stub
		long sum=0;
		boolean canCompute =(end-start)<THRESHOLD;
		if(canCompute){
			for(long i=start;i<=end;i++){
				sum+=i;
			}
		}else{
			long step=(start+end)/100;
			ArrayList<ForkJoinCountDemo> subTasks = new ArrayList<ForkJoinCountDemo>();
			long pos=start;
			for(int i=0;i<100;i++){
				long lastOne =pos+step;
				if(lastOne>end)
					lastOne=end;
				ForkJoinCountDemo subTask=new ForkJoinCountDemo(pos, lastOne);
				pos+=step+1;
				subTasks.add(subTask);
				subTask.fork();
			}
			for(ForkJoinCountDemo t:subTasks){
				sum+=t.join();
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ForkJoinPool forkJoinPool =new ForkJoinPool();
		ForkJoinCountDemo task= new ForkJoinCountDemo(0, 100000L);
		ForkJoinTask<Long> result = forkJoinPool.submit(task);
		try{
			long res= result.get();
			System.out.println("sum="+res);
		} catch(InterruptedException e){
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
