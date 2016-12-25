package Application;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SpringLayout;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import database.SqlConnection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class CompanyPanel extends JPanel {
	protected JTable table;
	private JTextField textField;
	JPopupMenu popup;
	
	public CompanyPanel() throws Exception {
		popup = new JPopupMenu();
		JMenuItem menuItemBalanceSheet = new JMenuItem("Balance Sheet");
		popup.add(menuItemBalanceSheet);
		JMenuItem menuItemCompanyValue = new JMenuItem("Company Value");
		popup.add(menuItemCompanyValue);
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 44, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		add(scrollPane);
		
		ResultSet res = Operations.writeTableResult();
		JTable table = new JTable(Operations.writeResult(res));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, -10, SpringLayout.EAST, this);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSearch, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblSearch, -6, SpringLayout.WEST, textField);
		add(lblSearch);
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String query = textField.getText();
				
				TableRowSorter<TableModel> rowSort = new TableRowSorter<>(table.getModel());
				table.setRowSorter(rowSort);
				
				rowSort.setRowFilter(RowFilter.regexFilter(query));
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("Pressed");
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					JTable source = (JTable)e.getSource();
					int row = source.rowAtPoint( e.getPoint() );
					int column = source.columnAtPoint( e.getPoint() );
					
					if (! source.isRowSelected(row)) 
						source.changeSelection(row, column, false, false);
					
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
}
