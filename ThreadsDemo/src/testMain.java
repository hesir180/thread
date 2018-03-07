
public class testMain {

	public static void main(String[] args) {
		UnsafeSequence uss = new UnsafeSequence();
		Outputer out = new Outputer();
		// TODO Auto-generated method stub
		Thread t1 = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try{
						Thread.sleep(500);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
					out.output("heweitong");
				}
			}
		};
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try{
						Thread.sleep(500);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
					out.output("shibushisb");
				}
			}
		});
		t1.start();
		t2.start();
		
		new Thread(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
		}}.start();
		
	}

}
