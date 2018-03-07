//静态方法，不需要实例化对象即可调用，一般用于工具类中供多次调用，静态方法直接加载进内存。
public class SingletonDemo{
	

/*
 * 恶汉单例模式
 * 缺点：Singleton实例在什么时候创建不受控制。对于静态成员instance，它会在类第一次初始化的时候被创建，
 * 这个时刻并不一定是getInstance()方法第一次被调用的时候。
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
 * 懒汉单例模式
 * 它只会在instance被第一次使用时，创建对象。
 * 为了防止对象被多次创建，我们不得不使用synchronized进行方法同步。
 * 缺点：在并发环境下加锁，竞争激烈的场合对性能可能产生一定得影响。
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
 *解决懒汉和恶汉单例缺点的方法 
 * 巧妙的使用内部类和类的初始化方式。利用虚拟机的类初始化机制创建单例
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