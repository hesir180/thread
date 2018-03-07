import java.util.concurrent.locks.ReentrantLock;
/*
 * tryLock(time,TimeUnit)������ȴ���ʱ�������������񲻻�ȫ�����
 * tryLock()���������̵߳ȴ���Ҳ�����������
 * */
public class tryLock implements Runnable{
	public static ReentrantLock lock1=new ReentrantLock();
	public static ReentrantLock lock2=new ReentrantLock();
	int lock;
	public tryLock(int lock){
		this.lock=lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(lock==1){
			while(true){
				if(lock1.tryLock()){
					try{
						try{
							Thread.sleep(500);
						}catch(InterruptedException e){}
						if(lock2.tryLock()){
							try{
								System.out.println(Thread.currentThread().getId()+":my job done");
								return;
							}finally{
								lock2.unlock();
							}
						}
					}finally{
						lock1.unlock();
					}
				}
			}
		}else{
			while(true){
				if(lock2.tryLock()){
					try{
						try{
							Thread.sleep(500);
						}catch(InterruptedException e){}
						if(lock1.tryLock()){
							try{
								System.out.println(Thread.currentThread().getId()+":my job done");
								return;
							}finally{
								lock1.unlock();
							}
						}
						
					}finally{
						lock2.unlock();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tryLock r1= new tryLock(1);
		tryLock r2= new tryLock(2);
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();
		t2.start();
	}
}
