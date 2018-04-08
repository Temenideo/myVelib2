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
		getContentPane().setLayout(null);
		
		Button btnDefaultSetup = new Button("Default Setup");
		btnDefaultSetup.setBounds(100, 25, 150, 25);
		btnDefaultSetup.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(btnDefaultSetup);
		
		Button btnCustomSetup = new Button("Custom Setup");
		btnCustomSetup.setBounds(100, 75, 150, 25);
		btnCustomSetup.setBackground(Color.LIGHT_GRAY);
		btnCustomSetup.setActionCommand("Custom Setu");
		getContentPane().add(btnCustomSetup);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), btnDefaultSetup, btnCustomSetup}));
		this.setSize(375,171);
		
		ActionListener defaultSet = new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				try {
					setup.startMyVelib(10,10,4000,4000,0.7,0.3);
				} catch (BadStateStationCreationException | BadTypeStationCreationException
						| BadParkingSlotCreationException | NoEndStationAvailableException e) {
					e.printStackTrace();
				}
				GUImainFrame mainFrame= new GUImainFrame();
				GUImainFrame.setxBoundary(4000);
				GUImainFrame.setyBoundary(4000);
				mainFrame.setVisible(true);
				GUImainFrame.refresh();
				dispose();
			}
			
		};
		ActionListener customSet = new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				display();
			}
				private void display() {
					GUICustomNetwork customNet = new GUICustomNetwork();
					customNet.setVisible(true);
					dispose();
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
