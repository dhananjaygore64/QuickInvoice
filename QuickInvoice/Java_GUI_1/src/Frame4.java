import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame4 extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	static int total,MRP30CheckboxFlag,MRP60CheckboxFlag;
	static int MRP30Quanty,MRP30Rate,MRP60Quanty,MRP60Rate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame4 frame = new Frame4();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private int makeTotal() {
		try {
			MRP30Quanty=Integer.parseInt(textField_2.getText());
			MRP30Rate=Integer.parseInt(textField.getText());
			MRP60Quanty=Integer.parseInt(textField_3.getText());
			MRP60Rate=Integer.parseInt(textField_1.getText());
		}catch(Exception ex) {
			total=0;
		}
		if((MRP30CheckboxFlag==1)&&(MRP60CheckboxFlag==0)) {			// only MRP 30 is selected
			total=(MRP30Quanty*MRP30Rate);
		}else if((MRP30CheckboxFlag==0)&&(MRP60CheckboxFlag==1)) {		// only MRP 60 is selected
			total=(MRP60Quanty*MRP60Rate);
		}else if((MRP30CheckboxFlag==1)&&(MRP60CheckboxFlag==1)) {		// Both MRP 30 and MRP 60 are selected
			total=((MRP30Quanty*MRP30Rate)+(MRP60Quanty*MRP60Rate));
		}else {
			total=0;
		}
		return total;
	}
	private int makeTotalofMRP30(int checkbox1) {
		int MRP30Quanty=0,MRP30Rate=0;
		if(checkbox1==1) {			// checkbox1 is selected
			try {
				MRP30Quanty=Integer.parseInt(textField_2.getText());
				MRP30Rate=Integer.parseInt(textField.getText());
				total+=(MRP30Quanty*MRP30Rate);
			}catch(Exception ex) {
				total=0;
			}

		}else if(checkbox1==0) {	// checkbox1 is unselected
			MRP30Quanty=Integer.parseInt(textField_2.getText());
			MRP30Rate=Integer.parseInt(textField.getText());
			total-=(MRP30Quanty*MRP30Rate);
		}
		if(total<0) {
			total=0;
		}
		return total;
	}
	private int makeTotalofMRP60(int checkbox2) {
		int MRP60Quanty=0,MRP60Rate=0;
		if(checkbox2==1) {			// checkbox2 is selected
			MRP60Quanty=Integer.parseInt(textField_3.getText());
			MRP60Rate=Integer.parseInt(textField_1.getText());
			total+=(MRP60Quanty*MRP60Rate);			
		}else if(checkbox2==0) {	// checkbox2 is unselected
			MRP60Quanty=Integer.parseInt(textField_3.getText());
			MRP60Rate=Integer.parseInt(textField_1.getText());
			total-=(MRP60Quanty*MRP60Rate);			
		}
		if(total<0) {
			total=0;
		}
		return total;
	}
	/**
	 * Create the frame.
	 */
	public Frame4() {
		setTitle("Item Selection");
		setMinimumSize(new Dimension(700, 500));
		setLocation(new Point(50, 100));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));

		JCheckBox chckbxMrp = new JCheckBox("M.R.P 60");
		chckbxMrp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxMrp.isSelected()) {
					MRP60CheckboxFlag=1;
					lblNewLabel_2.setText(Integer.toString(makeTotal()));
				}else {
					MRP60CheckboxFlag=0;
					lblNewLabel_2.setText(Integer.toString(makeTotal()));
				}

			}
		});
		chckbxMrp.setAlignmentX(Component.CENTER_ALIGNMENT);
		chckbxMrp.setAlignmentY(Component.TOP_ALIGNMENT);
		chckbxMrp.setBorderPainted(true);
		chckbxMrp.setBackground(new Color(204, 255, 255));

		JLabel lblNewLabel = new JLabel("Items");
		lblNewLabel.setBackground(new Color(255, 255, 255));

		JLabel lblRatePerDozen = new JLabel("Rate Per Dozen");
		lblRatePerDozen.setBackground(new Color(255, 255, 255));

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(MRP60CheckboxFlag==1) {
					lblNewLabel_2.setText(Integer.toString(makeTotal()));
				}
			}
		});
		textField_1.setBackground(new Color(204, 255, 255));
		textField_1.setAlignmentY(Component.TOP_ALIGNMENT);
		textField_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textField_1.setColumns(10);
		textField_1.setText("360");
		JCheckBox chckbxMrp_1 = new JCheckBox("M.R.P 30");
		chckbxMrp_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxMrp_1.isSelected()) {
					MRP30CheckboxFlag=1;
					lblNewLabel_2.setText(Integer.toString(makeTotal()));
				}else {
					MRP30CheckboxFlag=0;
					lblNewLabel_2.setText(Integer.toString(makeTotal()));
				}
			}
		});
		chckbxMrp_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		chckbxMrp_1.setAlignmentY(Component.TOP_ALIGNMENT);
		chckbxMrp_1.setBorderPainted(true);
		chckbxMrp_1.setBackground(new Color(204, 255, 255));

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(MRP30CheckboxFlag==1) {
					lblNewLabel_2.setText(Integer.toString(makeTotal()));
				}
			}
		});
		textField.setMaximumSize(new Dimension(10, 20));
		textField.setLocation(new Point(100, 200));
		textField.setAlignmentY(Component.TOP_ALIGNMENT);
		textField.setBackground(new Color(204, 255, 255));
		textField.setColumns(10);
		textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textField.setText("180");

		JButton btnNewButton = new JButton("Generate Invoice");
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExcelFileOperations.generateInvoice();

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(204, 102, 255));

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBackground(Color.WHITE);

		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(MRP30CheckboxFlag==1) {
					lblNewLabel_2.setText(Integer.toString(makeTotal()));
				}
			}
		});
		textField_2.setText("1");
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(204, 255, 255));
		textField_2.setAlignmentY(0.0f);
		textField_2.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(MRP60CheckboxFlag==1) {
					lblNewLabel_2.setText(Integer.toString(makeTotal()));
				}
			}
		});
		textField_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textField_3.setText("1");
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(204, 255, 255));
		textField_3.setAlignmentY(0.0f);

		JLabel lblNewLabel_1 = new JLabel("Total amount");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		//		JLabel lblNewLabel_2 = new JLabel("");
		//		lblNewLabel_2.setBackground(Color.WHITE);
		//		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(48)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblRatePerDozen, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGap(67)
						.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(15)
						.addComponent(chckbxMrp_1, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addGap(100)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addGap(55)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGap(48))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(15)
										.addComponent(chckbxMrp, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
										.addGap(100)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
										.addGap(55))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap(247, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
										.addGap(53)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
						.addGap(48))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(16)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(4)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblRatePerDozen, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGap(34)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxMrp_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGap(66)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxMrp, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGap(67)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGap(46)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(45, Short.MAX_VALUE))
				);
		contentPane.setLayout(gl_contentPane);
	}
}
