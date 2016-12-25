package Application;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Application {
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame;
				try {
					frame = new ApplicationFrame("Application");
					frame.setSize(1200, 600);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
			}
		});
		
	}

}
