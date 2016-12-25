package Application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class ApplicationFrame extends JFrame {
	
	private CompanyPanel companyPanel;
	
	public ApplicationFrame(String title) throws Exception {
		super(title);
		
		getContentPane().setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		JMenu menuOpen = new JMenu("Open");
		menuFile.add(menuOpen);
		JMenuItem menuItemCompanies = new JMenuItem("Companies");
		menuOpen.add(menuItemCompanies);
		
		companyPanel = new CompanyPanel();
		
		Container container = getContentPane();
		container.add(menuBar, BorderLayout.NORTH);
		
		menuItemCompanies.addActionListener (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
		    container.add(companyPanel, BorderLayout.CENTER);
		    revalidate();
		    repaint();
			}
		});
		
		
		
	}
}
