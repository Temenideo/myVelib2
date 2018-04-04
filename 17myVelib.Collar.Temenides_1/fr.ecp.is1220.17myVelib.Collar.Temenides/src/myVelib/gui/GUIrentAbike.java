package myVelib.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class GUIrentAbike extends JFrame {
	private JTextField textField;
	private JSplitPane splitPane;
	private JLabel lblIdStation;
	private JSplitPane splitPane_1;
	private JButton btnConfirm;
	private JButton btnReset;
	private JSplitPane splitPane_2;
	private JLabel lblIdUser;
	private JLabel lblTypeOfBike;
	private JSplitPane splitPane_3;
	private JTextField textField_1;
	private JComboBox comboBox;
	public GUIrentAbike() {
		
		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.WEST);
		textField.setColumns(10);
		
		splitPane = new JSplitPane();
		splitPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(splitPane, BorderLayout.NORTH);
		
		lblIdStation = new JLabel("ID Station");
		splitPane.setLeftComponent(lblIdStation);
		
		splitPane_2 = new JSplitPane();
		splitPane.setRightComponent(splitPane_2);
		
		lblIdUser = new JLabel("ID USER");
		splitPane_2.setLeftComponent(lblIdUser);
		
		lblTypeOfBike = new JLabel("Type of Bike");
		splitPane_2.setRightComponent(lblTypeOfBike);
		
		splitPane_1 = new JSplitPane();
		getContentPane().add(splitPane_1, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("Confirm");
		splitPane_1.setRightComponent(btnConfirm);
		
		btnReset = new JButton("Reset");
		splitPane_1.setLeftComponent(btnReset);
		
		splitPane_3 = new JSplitPane();
		getContentPane().add(splitPane_3, BorderLayout.CENTER);
		
		textField_1 = new JTextField();
		splitPane_3.setLeftComponent(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		splitPane_3.setRightComponent(comboBox);
	}
public static void main(String[] args) {
	GUIrentAbike bk = new GUIrentAbike();
	bk.setVisible(true);
}
}
