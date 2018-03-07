
public class Outputer {
	public void output(String name){
		int len= name.length();
		synchronized (Outputer.class) {
			for(int i = 0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
}
