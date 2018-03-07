//��̬����������Ҫʵ�������󼴿ɵ��ã�һ�����ڹ������й���ε��ã���̬����ֱ�Ӽ��ؽ��ڴ档
public class SingletonDemo{
	

/*
 * �񺺵���ģʽ
 * ȱ�㣺Singletonʵ����ʲôʱ�򴴽����ܿ��ơ����ھ�̬��Աinstance�����������һ�γ�ʼ����ʱ�򱻴�����
 * ���ʱ�̲���һ����getInstance()������һ�α����õ�ʱ��
 * */
public static class Singleton {
	private Singleton(){
		System.out.println("Singleton is create");
	}
	private static Singleton instance =new Singleton();
	private static Singleton getInstance(){
		return instance;
	}
}

/*
 * ��������ģʽ
 * ��ֻ����instance����һ��ʹ��ʱ����������
 * Ϊ�˷�ֹ���󱻶�δ��������ǲ��ò�ʹ��synchronized���з���ͬ����
 * ȱ�㣺�ڲ��������¼������������ҵĳ��϶����ܿ��ܲ���һ����Ӱ�졣
 * */
public  static class LazySingleton{
	private LazySingleton(){
		System.out.println("LazySingleton is create");
	}
	private static LazySingleton instance=null;
	private static synchronized LazySingleton getInstance(){
		if(instance == null){
			instance =new LazySingleton();
		}
		return instance;
	}
}
/*
 *��������Ͷ񺺵���ȱ��ķ��� 
 * �����ʹ���ڲ������ĳ�ʼ����ʽ����������������ʼ�����ƴ�������
 * */
public  static class StaticSingleton{
	private StaticSingleton(){
		System.out.println("StaticSingleton is create");
	}
	private static class SingletonHolder{
		private static StaticSingleton instance =new StaticSingleton();
	}
	public static StaticSingleton getInstance(){
		return SingletonHolder.instance;
	}
}
}