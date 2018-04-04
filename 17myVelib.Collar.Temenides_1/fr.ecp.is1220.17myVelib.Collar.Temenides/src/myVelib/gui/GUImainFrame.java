package myVelib.gui;

import javax.swing.JFrame;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JToolBar;

public class GUImainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable stationList;
	private JTable userList;
	private JButton btnRefresh;
	private PrintStream standardOut;

	public GUImainFrame() {
		super();
		setSize(1080,720);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panel = new JPanel();
		
		JToolBar toolBar = new JToolBar();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
					.addGap(18))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(926))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(87))
		);
		
		
		
		
		
		btnRefresh = new JButton("Refresh");
		toolBar.add(btnRefresh);
		btnRefresh.setBackground(UIManager.getColor("Button.background"));
		btnRefresh.setForeground(SystemColor.desktop);
		
		JButton button = new JButton("Start");
		toolBar.add(button);
		
		JButton button_1 = new JButton("Clear");
		toolBar.add(button_1);
		
				
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Console Output", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JTextArea textArea = new JTextArea(11,120);
		textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        // keeps reference of standard output stream
        standardOut = System.out;
         
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
        
		panel_1.add(textArea);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		userList = new JTable();
		userList.setCellSelectionEnabled(true);
		userList.setColumnSelectionAllowed(true);
		stationList = new JTable();
		stationList.setColumnSelectionAllowed(true);
		stationList.setCellSelectionEnabled(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(stationList);
		tabbedPane.addTab("Station List", null, scrollPane, null);
				
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(userList);
		tabbedPane.addTab("User List", null, scrollPane_1, null);
		getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnStation = new JMenu("Station");
		menuBar_1.add(mnStation);
		
		JMenuItem mntmAddStation = new JMenuItem("New Station");
		mntmAddStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StationCreation statCreate = new StationCreation();
				statCreate.setVisible(true);
			}
		});
		mnStation.add(mntmAddStation);
		
		JMenuItem mntmViewStation = new JMenuItem("View Station");
		mnStation.add(mntmViewStation);
		
		JMenu mnUser = new JMenu("User");
		menuBar_1.add(mnUser);
		
		JMenuItem mntmAddUser = new JMenuItem("New User");
		mntmAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserCreation userCreate = new UserCreation();
				userCreate.setVisible(true);
			}
		});
		
		mnUser.add(mntmAddUser);
		JMenuItem mntmViewUser = new JMenuItem("View User");
		mnUser.add(mntmViewUser);
		
		JMenu mnLocation = new JMenu("Location");
		menuBar_1.add(mnLocation);
		
		
		
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
					"User ID", "Last Name", "First Name","Card Type", "Total Use Time", "Total Charges", "Earned Credits"
				});
				for (User user:Reseau.getInstance().getUserList()) {
					userModel.addRow(new Object[] {user.getUserID(),user.getName(),user.getFirstName(),user.getCard().getTypeCard(), user.getTotalTime(),user.getTotalCharges(),user.getEarnedCredits()});	
				}
				userList.setModel(userModel);
			};
			
		};
		btnRefresh.addActionListener(refresh);
	}
}
