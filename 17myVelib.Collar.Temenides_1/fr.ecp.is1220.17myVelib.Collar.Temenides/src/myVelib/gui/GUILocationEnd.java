package myVelib.gui;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import myVelib.BadParkingSlotCreationException;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;
import myVelib.ridePolicies.NoEndStationAvailableException;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Choice;

public class GUILocationEnd extends JFrame{
	public GUILocationEnd() {
		setType(Type.UTILITY);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Location Creation");
		getContentPane().setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(30, 108, 100, 25);
		getContentPane().add(btnCancel);
		
		JPanel userPanel = new JPanel();
		userPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User Selection", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		userPanel.setBounds(30, 20, 180, 47);
		getContentPane().add(userPanel);
		userPanel.setLayout(null);
		
		Choice userChoice = new Choice();
		userChoice.setBounds(6, 18, 165, 22);
		for (User user : Reseau.getInstance().getUserList()) {
			if(user.getLoc()!=null) {
			userChoice.add(user.getUserID()+". "+user.getFirstName()+" "+user.getName());
			}
		}
			
		userPanel.add(userChoice);
		
		JButton btnConfirm = new JButton("End Location");
		btnConfirm.setBounds(283, 108, 150, 25);
		getContentPane().add(btnConfirm);
		
		JPanel stationPanel = new JPanel();
		stationPanel.setLayout(null);
		stationPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Return Station", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		stationPanel.setBounds(259, 20, 180, 47);
		getContentPane().add(stationPanel);
		
		Choice stationChoice = new Choice();
		stationChoice.setBounds(6, 18, 165, 22);
		for (Station stat:Reseau.getInstance().getStationList()) {
			stationChoice.add(stat.getName());
		}
		stationPanel.add(stationChoice);
	
	
	
	ActionListener confirm = new ActionListener() {
		public void actionPerformed(final ActionEvent ae) {
			ArrayList<User> userList = new ArrayList<User>();
			for(User user:Reseau.getInstance().getUserList()) {
				if(user.getLoc()!=null) {
					userList.add(user);
				}
			}
			User user = userList.get(userChoice.getSelectedIndex());
			Station arrival = Reseau.getInstance().getStationList().get(stationChoice.getSelectedIndex());
			try {
				user.getLoc().returnBike(arrival);
			} catch (BadParkingSlotCreationException | NoEndStationAvailableException e) {
				e.printStackTrace();
			}
			dispose();
		}
	};
	
	ActionListener cancel = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			dispose();
		}
		
	};
	btnCancel.addActionListener(cancel);
	btnConfirm.addActionListener(confirm);
	
	setSize(475,210);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
