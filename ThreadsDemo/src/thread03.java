
public class thread03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
		}.start();
	}

}
class ShareData{
	private int j;
	public synchronized void increment(){
		j++;
	}
}
