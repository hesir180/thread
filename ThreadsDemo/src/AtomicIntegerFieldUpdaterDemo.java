/*
 * 普通变量享受CAS线程安全
 * 场景：有些时候，由于初期考虑不周，或者后期的需求变化，一些普通变量可能也会有线程安全的需求
 * 改动原来的代码违背了开闭原则没如果系统使用这个变量的地方特别多，也很麻烦
 * 
 * AtomicIntegerFieldUpdater的几个注意事项：
 * 1.Updater只能修改它可见范围内的变量，如果score修改为private，就不可行了。
 * 2.为了确保变量被正确的读取，它必须是volatile类型的
 * 3.CAS操作会通过对象实例中的偏移量直接进行赋值，因此不支持static字段。
 * 
 * */

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {
	public static class Candidate{
		int id;
		volatile int score;
	}
	public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater
		= AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
	public static AtomicInteger allScore =new AtomicInteger(0);
	public static void main(String[] args) throws InterruptedException{
		final Candidate stu=new Candidate();
		Thread[] t= new Thread[10000];
		for(int i=0;i<10000;i++){
			t[i]=new Thread(){
				public void run(){
					if(Math.random()>0.4){
						scoreUpdater.incrementAndGet(stu);
						allScore.incrementAndGet();
					}
				}
			};
			t[i].start();
		}
		for(int i=0;i<10000;i++){
			t[i].join();
		}
		System.out.println("score="+stu.score);
		System.out.println("allScore="+allScore);
	}
}
