import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Frame1 {
	public static int newCustomerFlag;
	public static int oldCustomerFlag;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Frame1.class.getResource("/mainicon.png")));
		frame.setMinimumSize(new Dimension(700, 500));
		frame.setLocation(new Point(100, 50));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New Customer");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 51, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newCustomerFlag=1;
				oldCustomerFlag=0;
				frame.dispose();
				Frame2 fr2=new Frame2();
				fr2.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Frame1.class.getResource("/NewCustomer.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnOldCustomer = new JButton("Old Customer");
		btnOldCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newCustomerFlag=0;
				oldCustomerFlag=1;
				frame.dispose();
				Frame3 fr3=new Frame3();
				fr3.setVisible(true);			
			}
		});
		btnOldCustomer.setBackground(new Color(0, 204, 0));
		btnOldCustomer.setForeground(new Color(255, 255, 255));
		btnOldCustomer.setIcon(new ImageIcon(Frame1.class.getResource("/Manager.png")));
		btnOldCustomer.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnCustomerList = new JButton("Customer List");
		btnCustomerList.setBackground(new Color(204, 0, 255));
		btnCustomerList.setForeground(new Color(255, 255, 255));
		btnCustomerList.setIcon(new ImageIcon(Frame1.class.getResource("/List1.png")));
		btnCustomerList.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		Box verticalBox = Box.createVerticalBox();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(255)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(255)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCustomerList, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(btnOldCustomer, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
					.addGap(251))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(329)
					.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(355, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(101)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOldCustomer, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(btnCustomerList, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
