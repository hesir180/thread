import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ExecutorServiceParalegalSum {
	private int coreCpuNum;
	private ExecutorService executor;
	private List<FutureTask<Long>> tasks =new ArrayList<FutureTask<Long>>();
}
