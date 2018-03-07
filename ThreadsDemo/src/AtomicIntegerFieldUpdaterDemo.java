/*
 * ��ͨ��������CAS�̰߳�ȫ
 * ��������Щʱ�����ڳ��ڿ��ǲ��ܣ����ߺ��ڵ�����仯��һЩ��ͨ��������Ҳ�����̰߳�ȫ������
 * �Ķ�ԭ���Ĵ���Υ���˿���ԭ��û���ϵͳʹ����������ĵط��ر�࣬Ҳ���鷳
 * 
 * AtomicIntegerFieldUpdater�ļ���ע�����
 * 1.Updaterֻ���޸����ɼ���Χ�ڵı��������score�޸�Ϊprivate���Ͳ������ˡ�
 * 2.Ϊ��ȷ����������ȷ�Ķ�ȡ����������volatile���͵�
 * 3.CAS������ͨ������ʵ���е�ƫ����ֱ�ӽ��и�ֵ����˲�֧��static�ֶΡ�
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
