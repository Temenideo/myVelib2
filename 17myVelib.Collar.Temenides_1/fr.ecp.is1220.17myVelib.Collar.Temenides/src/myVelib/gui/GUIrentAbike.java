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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GUIrentAbike extends JFrame {
	private JTextField textField;
	public GUIrentAbike() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(164, 69, 128, 70);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 18, 116, 45);
		panel.add(textField);
		textField.setAlignmentX(50.0f);
		textField.setColumns(10);
	}
public static void main(String[] args) {
	GUIrentAbike bk = new GUIrentAbike();
	bk.setVisible(true);
}
}
