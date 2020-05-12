import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class Frame3 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static int noOfClickFlag=0;
	public static int prevJtableRowValue=0;
	public static int clickedRow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame3 frame = new Frame3();
					//					frame = new Frame3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame3() {
		setBackground(new Color(128, 128, 128));
		setTitle("Old Customer List");
		setMinimumSize(new Dimension(700, 500));
		setLocation(new Point(50, 100));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(39)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
						.addGap(44))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(35)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
						.addGap(30))
				);

		table = new JTable() {
			/* Below code will disable Editing in Jtable */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
			/* Up to this */
		};
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setBackground(new Color(250, 250, 210));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				noOfClickFlag++;						// Number of Click Flag will increment when user clicks on the any Row of Table
				if(noOfClickFlag>=2) {					// When Double clicked by user
					clickedRow=table.getSelectedRow();		// Get Second Clicked Row value 
					if(clickedRow==prevJtableRowValue) {		// if both values are same that means user has selected same row for twice
//						try {
////							System.out.println("TRY BLOCK");
//							DataBaseOperations.res.beforeFirst();
//							while(DataBaseOperations.res.next()){
//								System.out.println("TRY BLOCK");
//								System.out.println(DataBaseOperations.res.getInt(1)+"  "+DataBaseOperations.res.getString(2)+"  "+DataBaseOperations.res.getString(3));	// STEP 6- Process result
//							}
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						System.out.println(clickedRow);
						Frame4 fr4=new Frame4();
						fr4.setVisible(true);
						noOfClickFlag=0;
						dispose();
					}else {
						noOfClickFlag=1;
					}
				}
				prevJtableRowValue=table.getSelectedRow();		// Get First Clicked Row value 
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Name", "Address Line 1", "Address Line 2", "State Name", "Contact", "PAN Number"
				}
				) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, Object.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		DataBaseOperations.getCustomerList();
		table.setModel(DbUtils.resultSetToTableModel(DataBaseOperations.res));
	}
}
