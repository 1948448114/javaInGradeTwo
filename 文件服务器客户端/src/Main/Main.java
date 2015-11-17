package Main;
import java.awt.EventQueue;
import Frame.FileFrame;
import Frame.test;
public class Main {
	public static int ThreadNumber=5;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileFrame frame=new FileFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}


