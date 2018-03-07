import java.util.Random;

public class ThreadScopeShareData {
	private static int data = 0;
	public static void main(String []args){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				data = new Random().nextInt();
				System.out.println(data);
				new A().get();
				new B().get();
			}
		}).start();
	}
	static class A{
		public int get(){
			return data;
		}
	}
	static class B{
		public int get(){
			return data;
		}
	}
}
