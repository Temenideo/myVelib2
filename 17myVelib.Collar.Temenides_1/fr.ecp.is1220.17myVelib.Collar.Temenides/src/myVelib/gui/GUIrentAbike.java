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
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Dimension;

public class GUIrentAbike extends JFrame {
	private JTextField textField;
	public GUIrentAbike() {
		
		textField = new JTextField();
		textField.setSize(new Dimension(10, 10));
		textField.setAlignmentX(50.0f);
		textField.setBounds(new Rectangle(200, 0, 0, 0));
		getContentPane().add(textField, BorderLayout.WEST);
		textField.setColumns(10);
	}
public static void main(String[] args) {
	GUIrentAbike bk = new GUIrentAbike();
	bk.setVisible(true);
}
}
