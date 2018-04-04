package myVelib.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import myVelib.User;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;

public class UserCreation extends JFrame{
	public UserCreation() {
		setType(Type.UTILITY);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("New User");
		getContentPane().setLayout(null);
		
		JPanel namePanel = new JPanel();
		namePanel.setBounds(10, 10, 415, 50);
		getContentPane().add(namePanel);
		namePanel.setLayout(null);
		
		JPanel firstNamePanel = new JPanel();
		firstNamePanel.setBounds(10, 0, 180, 50);
		namePanel.add(firstNamePanel);
		firstNamePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "First Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		firstNamePanel.setLayout(null);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(10, 18, 160, 25);
		firstNamePanel.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setBounds(230, 0, 180, 50);
		namePanel.add(lastNamePanel);
		lastNamePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Last Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lastNamePanel.setLayout(null);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(10, 18, 160, 25);
		lastNamePanel.add(txtLastName);
		txtLastName.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(320, 123, 100, 25);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 123, 100, 25);
		getContentPane().add(btnCancel);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Subscripton Option", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		cardPanel.setBounds(10, 65, 415, 52);
		getContentPane().add(cardPanel);
		cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton rdbtnNoCard = new JRadioButton("No Card");
		rdbtnNoCard.setSelected(true);
		buttonGroup.add(rdbtnNoCard);
		cardPanel.add(rdbtnNoCard);
		
		JRadioButton rdbtnVlibreCard = new JRadioButton("VLibre Card");
		buttonGroup.add(rdbtnVlibreCard);
		cardPanel.add(rdbtnVlibreCard);
		
		JRadioButton rdbtnVmaxCard = new JRadioButton("VMax Card");
		buttonGroup.add(rdbtnVmaxCard);
		cardPanel.add(rdbtnVmaxCard);
	
	ActionListener confirm = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			createUser();
			dispose();
			}
		public void createUser(){
			String name= txtLastName.getText();
			String firstName = txtFirstName.getText();
			User user = new User(name,firstName);
			if(rdbtnVlibreCard.isSelected()) {
				user.subscribeVlibre();
			}
			if(rdbtnVmaxCard.isSelected()) {
				user.subscribeVmax();
			}
	}
		
	};
	
	ActionListener cancel = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			dispose();
		}
		
	};
	
	btnConfirm.addActionListener(confirm);	
	btnCancel.addActionListener(cancel);
	
	
	setSize(450,199);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
}
