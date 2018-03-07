import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	
	public static class Soldier implements Runnable{
		private String soldier;
		private final CyclicBarrier cyclic;
		Soldier(CyclicBarrier cyclic,String soldierName){
			this.cyclic=cyclic;
			this.soldier = soldierName;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				//�ȴ�����ʿ������
				cyclic.await();
				doWork();
				//�ȴ�����ʿ����ɹ���
				cyclic.await();
			} catch(InterruptedException e){
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		void doWork(){
			try{
				Thread.sleep(Math.abs(new Random().nextInt()%1000));
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(soldier+":�������");
		}
		
	}
	
	public static class BarrierRun implements Runnable{
		boolean flag;
		int N;
		public BarrierRun(boolean flag,int N){
			this.flag=flag;
			this.N=N;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(flag){
				System.out.println("˾��:[ʿ��"+N+"����������ɣ�]");
			}else{
				System.out.println("˾��:[ʿ��"+N+"����������ϣ�]");
				flag=true;
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final int N=10;
		boolean flag =false;
		Thread[] allSoldier =new Thread[N];
		CyclicBarrier cyclic =new CyclicBarrier(N, new BarrierRun(flag, N));
		//�������ϵ㣬��Ҫ��Ϊ��ִ���������
		System.out.println("���϶��飡");
		for(int i=0;i<N;i++){
			System.out.println("ʿ��"+i+"������");
			allSoldier[i]=new Thread(new Soldier(cyclic, "ʿ��"+i));
			allSoldier[i].start();
		/*	if(i==5){
				allSoldier[0].interrupt();
			}*/
		}
	}

}
