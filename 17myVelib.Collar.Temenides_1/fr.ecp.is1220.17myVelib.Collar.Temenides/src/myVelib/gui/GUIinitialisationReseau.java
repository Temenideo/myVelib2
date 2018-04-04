package myVelib.gui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.setup;
import myVelib.ridePolicies.NoEndStationAvailableException;

import java.awt.Component;
import java.awt.Color;

public class GUIinitialisationReseau extends JFrame{
	public GUIinitialisationReseau() {
		setTitle("Network Initialization");
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		Button btnDefaultSetup = new Button("Default Setup");
		btnDefaultSetup.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(btnDefaultSetup);
		
		Button btnCustomSetup = new Button("Custom Setup");
		btnCustomSetup.setBackground(Color.LIGHT_GRAY);
		btnCustomSetup.setActionCommand("Custom Setu");
		getContentPane().add(btnCustomSetup);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), btnDefaultSetup, btnCustomSetup}));
		this.setSize(800,600);
		
		ActionListener defaultSet = new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				try {
					display();
				} catch (BadStateStationCreationException | BadTypeStationCreationException
						| BadParkingSlotCreationException | NoEndStationAvailableException e) {
					e.printStackTrace();
				}
			}
				private void display() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
					setup.startMyVelib(10, 10);
					GUImainFrame2 mainFrame= new GUImainFrame2();
					mainFrame.setVisible(true);
					dispose();
			}
			
		};
		ActionListener customSet = new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				display();
			}
				private void display() {
					JTextField text = new JTextField("Custom");
					JPanel panel3 = new JPanel();
					panel3.add(text);
					getContentPane().add(panel3);
					setVisible(true);
			}
			
		};
		btnDefaultSetup.addActionListener(defaultSet);
		btnCustomSetup.addActionListener(customSet);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	public static void main(String[] args) {
		GUIinitialisationReseau gn = new GUIinitialisationReseau();
		gn.setVisible(true);
		
	}
}
