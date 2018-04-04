package myVelib.gui;

import javax.swing.JFrame;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.Color;

public class GUImainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable stationList;
	private JTable userList;
	private JButton btnRefresh;

	public GUImainFrame() {
		super();
		setSize(800,600);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		getContentPane().add(tabbedPane);
		
		stationList = new JTable();
		
		tabbedPane.addTab("Station List", null, stationList, null);
		
		userList = new JTable();
		tabbedPane.addTab("User List", null, userList, null);
		
		
		
		
		
		btnRefresh = new JButton("Refresh");
		tabbedPane.addTab("New tab", null, btnRefresh, null);
		btnRefresh.setForeground(new Color(0, 0, 0));
		
		
		ActionListener refresh = new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				refreshStations();
				refreshUsers();
				}
			public void refreshStations() {
				DefaultTableModel stationModel=new DefaultTableModel(new Object[][] {
				},
				new String[] {
					"Station ID", "Name", "Type", "Status", "Bikes", "Free Slots","Location"
				}
			);
				for (Station stat:Reseau.getInstance().getStationList()) {
						stationModel.addRow(new Object[] {stat.getStationID(),stat.getName(),stat.getTypeStation(),stat.getState(),stat.getFreeBikes(),stat.getFreeSlots(),stat.getPosition()});	
					}
				stationList.setModel(stationModel);
			};
			public void refreshUsers() {
				DefaultTableModel userModel=new DefaultTableModel(new Object[][] {
				},
				new String[] {
					"Station ID", "Name", "Type", "Status", "Bikes", "Free Slots","Location"
				});
				for (User user:Reseau.getInstance().getUserList()) {
					userModel.addRow(new Object[] {user.getUserID(),user.getName(),user.getFirstName(),user.getTotalTime(),user.getTotalCharges(),user.getEarnedCredits()});	
				}
				userList.setModel(userModel);
			};
			
		};
		btnRefresh.addActionListener(refresh);
	}
}
