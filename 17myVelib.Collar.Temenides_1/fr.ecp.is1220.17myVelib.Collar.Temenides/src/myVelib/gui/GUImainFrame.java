package myVelib.gui;

import javax.swing.JFrame;

import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.Location;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;
import myVelib.SortingStations.LeastOccupied;
import myVelib.SortingStations.MostUsed;
import myVelib.ridePolicies.NoEndStationAvailableException;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultCaret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JToolBar;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class GUImainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UserTable usersList;
	private static StationTable stationsList;
	private static LocationTable locationsList;
	public static double xBoundary;
	public static double yBoundary;
	

	JTextField uId = new JTextField(10);
	JTextField uName = new JTextField(30);
	JTextField uCard = new JTextField(10);
	JTextField uCharge = new JTextField(8);
	JTextField uTime = new JTextField(8);
	JTextField uCredits = new JTextField(8);
	JTextField sId = new JTextField(10);
	JTextField sName = new JTextField(30);
	JTextField sType = new JTextField(10);
	JTextField sStatus = new JTextField(8);
	JTextField sEBikes = new JTextField(4);
	JTextField sMBikes = new JTextField(4);
	JTextField sParking = new JTextField(4);
	JTextField sPosition = new JTextField(8);
	JRadioButton rdbtnOffline = new JRadioButton("Offline");
	JRadioButton rdbtnOnService = new JRadioButton("On Service");
	JRadioButton rdbtnPlusType = new JRadioButton("Plus");
	JRadioButton rdbtnStandardType = new JRadioButton("Standard");
	
	
	private final ButtonGroup StatusButtonGroup = new ButtonGroup();
	private final ButtonGroup typeButtonGroup = new ButtonGroup();
	private JTextField lUser;
	private JTextField lDeparture;
	private JTextField lArrival;
	private JTextField lPolicy;
	private JTextField lStartStation;
	private JTextField lEndStation;
	private JTextField lStartTime;
	private JTextField lEndTime;

	public GUImainFrame() {
		super();
		setTitle("MyVelib");
		setSize(1920,1000);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		CapturePane consoleOut = new CapturePane();
		JPanel consolePanel = new JPanel();
		consolePanel.setBounds(0, 0, 400, 400);
		consolePanel.add(consoleOut);
		consolePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Console Output", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PrintStream ps = System.out;
        System.setOut(new PrintStream(new StreamCapturer(consoleOut, ps)));
        
		JToolBar toolBar = new JToolBar();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(consolePanel, GroupLayout.DEFAULT_SIZE, 1890, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tabbedPane)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(1789)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(consolePanel, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		ActionListener addStation = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StationCreation statCreate = new StationCreation();
				statCreate.setVisible(true);
				refresh();
			}
		};
		
		ActionListener addUser = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserCreation userCreate = new UserCreation();
				userCreate.setVisible(true);
				refresh();
			}
		};
		
		ActionListener endLocation = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILocationEnd locationEnd = new GUILocationEnd();
				locationEnd.setVisible(true);
				refresh();
			}
		};
		
		
		usersList = new UserTable();
		stationsList = new StationTable();
		locationsList = new LocationTable();
		getContentPane().setLayout(groupLayout);
		
		JPanel stationPanel = new JPanel();
		tabbedPane.addTab("Stations", null, stationPanel, null);
		stationPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JScrollPane stationScroll = new JScrollPane();
		stationScroll.setViewportView(stationsList);
		stationPanel.add(stationScroll);
		
		JPanel stationInfoPanel = new JPanel();
		stationInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Station Info", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		stationInfoPanel.setLayout(null);
		stationPanel.add(stationInfoPanel);
		
		JPanel sIDPanel = new JPanel();
		sIDPanel.setLayout(null);
		sIDPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Station ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sIDPanel.setBounds(25, 20, 130, 47);
		stationInfoPanel.add(sIDPanel);
		
		
		sId.setBounds(8, 15, 115, 25);
		sIDPanel.add(sId);
		
		JPanel sNamePanel = new JPanel();
		sNamePanel.setLayout(null);
		sNamePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sNamePanel.setBounds(175, 20, 275, 47);
		stationInfoPanel.add(sNamePanel);
		
		
		sName.setBounds(8, 18, 260, 22);
		sNamePanel.add(sName);
		
		JPanel sTypePanel = new JPanel();
		sTypePanel.setLayout(null);
		sTypePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sTypePanel.setBounds(25, 85, 130, 47);
		stationInfoPanel.add(sTypePanel);
		
		
		sType.setBounds(8, 18, 115, 22);
		sTypePanel.add(sType);
		
		JPanel sStatusPanel = new JPanel();
		sStatusPanel.setLayout(null);
		sStatusPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Status", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sStatusPanel.setBounds(175, 85, 130, 47);
		stationInfoPanel.add(sStatusPanel);
		
		
		sStatus.setBounds(8, 18, 115, 22);
		sStatusPanel.add(sStatus);
		
		JPanel sMbikePanel = new JPanel();
		sMbikePanel.setLayout(null);
		sMbikePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mechanical Bikes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sMbikePanel.setBounds(25, 145, 130, 47);
		stationInfoPanel.add(sMbikePanel);
		
		
		sMBikes.setBounds(8, 18, 115, 22);
		sMbikePanel.add(sMBikes);
		
		JPanel sEbikePanel = new JPanel();
		sEbikePanel.setLayout(null);
		sEbikePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Electrical Bikes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sEbikePanel.setBounds(175, 145, 130, 47);
		stationInfoPanel.add(sEbikePanel);
		
		
		sEBikes.setBounds(8, 18, 115, 22);
		sEbikePanel.add(sEBikes);
		
		JPanel sParkingPanel = new JPanel();
		sParkingPanel.setLayout(null);
		sParkingPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Free Parking Slots", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sParkingPanel.setBounds(320, 145, 130, 47);
		stationInfoPanel.add(sParkingPanel);
		
		sParking.setBounds(8, 18, 115, 22);
		sParkingPanel.add(sParking);
		
		JPanel sPositionPanel = new JPanel();
		sPositionPanel.setLayout(null);
		sPositionPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Coordinates", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sPositionPanel.setBounds(320, 85, 130, 47);
		stationInfoPanel.add(sPositionPanel);
		
		sPosition.setBounds(8, 18, 115, 22);
		sPositionPanel.add(sPosition);
		
		JButton btnNewStation = new JButton("New Station");
		btnNewStation.setBounds(25, 310, 130, 25);
		btnNewStation.addActionListener(addStation);
		
		JPanel editionPanel = new JPanel();
		editionPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Edition ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		editionPanel.setBounds(493, 85, 313, 278);
		stationInfoPanel.add(editionPanel);
		editionPanel.setLayout(null);
		
		JPanel sStatusChangePanel = new JPanel();
		sStatusChangePanel.setBounds(6, 83, 275, 50);
		editionPanel.add(sStatusChangePanel);
		sStatusChangePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Status", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sStatusChangePanel.setLayout(null);
		
		
		
		rdbtnOnService.setBounds(30, 18, 100, 25);
		sStatusChangePanel.add(rdbtnOnService);
		StatusButtonGroup.add(rdbtnOnService);
		
		rdbtnOffline.setBounds(170, 18, 100, 25);
		sStatusChangePanel.add(rdbtnOffline);
		StatusButtonGroup.add(rdbtnOffline);
		StatusButtonGroup.add(rdbtnOffline);
		
		JPanel sTypeChangePanel = new JPanel();
		sTypeChangePanel.setBounds(6, 18, 275, 50);
		editionPanel.add(sTypeChangePanel);
		sTypeChangePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sTypeChangePanel.setLayout(null);
		typeButtonGroup.add(rdbtnPlusType);
		
		
		
		rdbtnPlusType.setBounds(30, 18, 100, 25);
		sTypeChangePanel.add(rdbtnPlusType);
		typeButtonGroup.add(rdbtnStandardType);
		
		rdbtnStandardType.setBounds(170, 18, 100, 25);
		sTypeChangePanel.add(rdbtnStandardType);
		
		JPanel sortPanel = new JPanel();
		sortPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sort Stations by...", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sortPanel.setBounds(25, 277, 148, 86);
		stationInfoPanel.add(sortPanel);
		sortPanel.setLayout(null);
		
		JButton btnLeast = new JButton("Least Occupied\r\n");
		btnLeast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeastOccupied sort = new LeastOccupied();
				try {
					sort.sortStation(Reseau.getInstance().getStationList());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLeast.setBounds(6, 18, 136, 25);
		sortPanel.add(btnLeast);
		
		JButton btnMost = new JButton("Most Used");
		btnMost.setBounds(6, 54, 136, 25);
		btnMost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostUsed sort = new MostUsed();
				try {
					sort.sortStation(Reseau.getInstance().getStationList());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		sortPanel.add(btnMost);
		
		
		JPanel userPanel = new JPanel();
		tabbedPane.addTab("Users", null, userPanel, null);
		GridBagLayout gbl_userPanel = new GridBagLayout();
		gbl_userPanel.columnWidths = new int[]{939, 939, 0};
		gbl_userPanel.rowHeights = new int[]{402, 0};
		gbl_userPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_userPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		userPanel.setLayout(gbl_userPanel);
		JScrollPane userScroll = new JScrollPane();
		userScroll.setViewportView(usersList);
		GridBagConstraints gbc_userScroll = new GridBagConstraints();
		gbc_userScroll.fill = GridBagConstraints.BOTH;
		gbc_userScroll.insets = new Insets(0, 0, 0, 5);
		gbc_userScroll.gridx = 0;
		gbc_userScroll.gridy = 0;
		userPanel.add(userScroll, gbc_userScroll);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBorder(new TitledBorder(null, "User Info", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_userInfoPanel = new GridBagConstraints();
		gbc_userInfoPanel.fill = GridBagConstraints.BOTH;
		gbc_userInfoPanel.gridx = 1;
		gbc_userInfoPanel.gridy = 0;
		userPanel.add(userInfoPanel, gbc_userInfoPanel);
		userInfoPanel.setLayout(null);
		
		JPanel uIDPanel = new JPanel();
		uIDPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		uIDPanel.setBounds(15, 20, 128, 47);
		userInfoPanel.add(uIDPanel);
		uIDPanel.setLayout(null);
		uId.setBounds(10, 15, 110, 25);
		uIDPanel.add(uId);
		JPanel uNamePanel = new JPanel();
		
		
		uNamePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		uNamePanel.setBounds(155, 20, 348, 47);
		userInfoPanel.add(uNamePanel);
		uNamePanel.setLayout(null);
		uName.setBounds(6, 18, 336, 22);
		uNamePanel.add(uName);
		JPanel uSubPanel = new JPanel();
		uSubPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Subscription", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		uSubPanel.setBounds(15, 85, 128, 47);
		userInfoPanel.add(uSubPanel);
		uSubPanel.setLayout(null);
		uCard.setBounds(6, 18, 116, 22);
		uSubPanel.add(uCard);
		JPanel uChargePanel = new JPanel();
		uChargePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Total Charge", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		uChargePanel.setBounds(275, 85, 106, 47);
		userInfoPanel.add(uChargePanel);
		uChargePanel.setLayout(null);
		uCharge.setBounds(8, 18, 90, 22);
		uChargePanel.add(uCharge);
		JPanel uTimePanel = new JPanel();
		uTimePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Total Time", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		uTimePanel.setBounds(155, 85, 106, 47);
		userInfoPanel.add(uTimePanel);
		uTimePanel.setLayout(null);
		uTime.setBounds(8, 18, 90, 22);
		uTimePanel.add(uTime);
		JPanel uCreditsPanel = new JPanel();
		uCreditsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Earned Credits", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		uCreditsPanel.setBounds(395, 85, 106, 47);
		userInfoPanel.add(uCreditsPanel);
		uCreditsPanel.setLayout(null);
		uCredits.setBounds(8, 18, 90, 22);
		uCreditsPanel.add(uCredits);
		
		JButton btnNewUser = new JButton("New User");
		btnNewUser.setBounds(25, 310, 130, 25);
		btnNewUser.addActionListener(addUser);
		
		JPanel locationPanel = new JPanel();
		tabbedPane.addTab("Locations", null, locationPanel, null);
		tabbedPane.setEnabledAt(2, true);
		GridBagLayout gbl_locationPanel = new GridBagLayout();
		gbl_locationPanel.columnWidths = new int[]{939, 939, 0};
		gbl_locationPanel.rowHeights = new int[]{402, 0};
		gbl_locationPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_locationPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		locationPanel.setLayout(gbl_locationPanel);
		
		JScrollPane locationScroll = new JScrollPane();
		locationScroll.setViewportView(locationsList);
		GridBagConstraints gbc_locationScroll = new GridBagConstraints();
		gbc_locationScroll.fill = GridBagConstraints.BOTH;
		gbc_locationScroll.insets = new Insets(0, 0, 0, 5);
		gbc_locationScroll.gridx = 0;
		gbc_locationScroll.gridy = 0;
		locationPanel.add(locationScroll,gbc_locationScroll);
		
		JPanel locationInfoPanel = new JPanel();
		locationInfoPanel.setLayout(null);
		locationInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Locations", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_locationInfoPanel = new GridBagConstraints();
		gbc_locationInfoPanel.fill = GridBagConstraints.BOTH;
		gbc_locationInfoPanel.gridx = 1;
		gbc_locationInfoPanel.gridy = 0;
		locationPanel.add(locationInfoPanel, gbc_locationInfoPanel);
		
		JPanel lUserPanel = new JPanel();
		lUserPanel.setLayout(null);
		lUserPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lUserPanel.setBounds(15, 20, 140, 47);
		locationInfoPanel.add(lUserPanel);
		
		lUser = new JTextField(10);
		lUser.setBounds(10, 18, 120, 22);
		lUserPanel.add(lUser);
		
		JPanel lStartPanel = new JPanel();
		lStartPanel.setLayout(null);
		lStartPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Departure", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lStartPanel.setBounds(165, 20, 140, 47);
		locationInfoPanel.add(lStartPanel);
		
		lDeparture = new JTextField(30);
		lDeparture.setBounds(10, 18, 120, 22);
		lStartPanel.add(lDeparture);
		
		JPanel lEndPanel = new JPanel();
		lEndPanel.setLayout(null);
		lEndPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Arrival", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lEndPanel.setBounds(165, 85, 140, 47);
		locationInfoPanel.add(lEndPanel);
		
		lArrival = new JTextField(10);
		lArrival.setBounds(10, 18, 116, 22);
		lEndPanel.add(lArrival);
		
		JPanel lPolicyPanel = new JPanel();
		lPolicyPanel.setLayout(null);
		lPolicyPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ride Policy", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lPolicyPanel.setBounds(15, 85, 140, 47);
		locationInfoPanel.add(lPolicyPanel);
		
		lPolicy = new JTextField(8);
		lPolicy.setBounds(8, 18, 120, 22);
		lPolicyPanel.add(lPolicy);
		
		JPanel lsStationPanel = new JPanel();
		lsStationPanel.setLayout(null);
		lsStationPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Departure Station", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lsStationPanel.setBounds(320, 20, 130, 47);
		locationInfoPanel.add(lsStationPanel);
		
		lStartStation = new JTextField(8);
		lStartStation.setBounds(10, 18, 110, 22);
		lsStationPanel.add(lStartStation);
		
		JPanel leStationPanel = new JPanel();
		leStationPanel.setLayout(null);
		leStationPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Arrival Station", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		leStationPanel.setBounds(320, 85, 130, 47);
		locationInfoPanel.add(leStationPanel);
		
		lEndStation = new JTextField(8);
		lEndStation.setBounds(10, 18, 110, 22);
		leStationPanel.add(lEndStation);
		
		JButton btnNewLocation = new JButton("New Location\r\n");
		btnNewLocation.setBounds(25, 310, 130, 25);
		
		JPanel lsTimePanel = new JPanel();
		lsTimePanel.setLayout(null);
		lsTimePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Departure Time", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lsTimePanel.setBounds(460, 20, 250, 47);
		locationInfoPanel.add(lsTimePanel);
		
		lStartTime = new JTextField(8);
		lStartTime.setBounds(8, 18, 230, 22);
		lsTimePanel.add(lStartTime);
		
		JPanel leTimePanel = new JPanel();
		leTimePanel.setLayout(null);
		leTimePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Arrival Time", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		leTimePanel.setBounds(460, 85, 250, 47);
		locationInfoPanel.add(leTimePanel);
		
		lEndTime = new JTextField(8);
		lEndTime.setBounds(8, 18, 230, 22);
		leTimePanel.add(lEndTime);
		
		JButton btnEndLocation = new JButton("End Location");
		btnEndLocation.setBounds(580, 310, 130, 25);
		btnEndLocation.addActionListener(endLocation);
		
		
		ActionListener locationListener = new ActionListener() {
			public void actionPerformed (final ActionEvent ae) {
				GUILocation loc = new GUILocation();
				loc.setVisible(true);
			}
		};
		btnNewLocation.addActionListener(locationListener);
		
		
		ActionListener sStatusListener= new ActionListener() {
			public void actionPerformed (final ActionEvent ae) {
				int i = stationsList.getTable().getSelectedRow();
				if(rdbtnOnService.isSelected()) {
					try {
						Reseau.getInstance().getStationList().get(i).setState("On Service");
					} catch (BadStateStationCreationException | NoEndStationAvailableException e) {
						e.printStackTrace();
					}
				}else if (rdbtnOffline.isSelected()) {
					try {
						Reseau.getInstance().getStationList().get(i).setState("Offline");
					} catch (BadStateStationCreationException | NoEndStationAvailableException e) {
						e.printStackTrace();
					}
					}
				refresh();
					
				
			}
		};
		ActionListener sTypeListener = new ActionListener() {
			public void actionPerformed (final ActionEvent ae) {
				int i = stationsList.getTable().getSelectedRow();
				if(rdbtnPlusType.isSelected()) {
					try {
						Reseau.getInstance().getStationList().get(i).setTypeStation("Plus");
					} catch (BadTypeStationCreationException e) {
						
					}
				}else if(rdbtnStandardType.isSelected()) {
					try {
						Reseau.getInstance().getStationList().get(i).setTypeStation("Standard");
					} catch (BadTypeStationCreationException e) {
						
					}
				}
				refresh();
			}
		};
		
		rdbtnPlusType.addActionListener(sTypeListener);
		rdbtnStandardType.addActionListener(sTypeListener);
		
		rdbtnOffline.addActionListener(sStatusListener);
		rdbtnOnService.addActionListener(sStatusListener);
		
		
		toolBar.add(btnNewStation);
		toolBar.add(btnNewUser);
		toolBar.add(btnNewLocation);
		toolBar.add(btnEndLocation);
		
	}
	
	public class CapturePane extends JPanel implements Consumer {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTextArea output;

        public CapturePane() {
            setLayout(new BorderLayout());
            output = new JTextArea(10,80);
            JScrollPane sp = new JScrollPane(output);
            sp.setPreferredSize(new Dimension(1650,350));
            add(sp,BorderLayout.CENTER);
        }

        @Override
        public void appendText(final String text) {
        	DefaultCaret caret = (DefaultCaret)output.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        	if (EventQueue.isDispatchThread()) {
                output.append(text);
             } else {

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        appendText(text);
                    }
                });

            }
        }        
    }

    public interface Consumer {        
        public void appendText(String text);        
    }

    public class StreamCapturer extends OutputStream {

        private StringBuilder buffer;
        private Consumer consumer;
        private PrintStream old;

        public StreamCapturer(Consumer consumer, PrintStream old) {
            buffer = new StringBuilder(128);
            this.old = old;
            this.consumer = consumer;
        }

        @Override
        public void write(int b) throws IOException {
            char c = (char) b;
            String value = Character.toString(c);
            buffer.append(value);
            if (value.equals("\n")) {
                consumer.appendText(buffer.toString());
                buffer.delete(0, buffer.length());
            }
            old.print(c);
        }        
    }
    
    class UserTable extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JTable table;
        private final TableModel tableModel;
        private final ListSelectionModel listSelectionModel;

    private void setFields(int index) {
        uId.setText(table.getValueAt(index, 0).toString());
        uName.setText(table.getValueAt(index, 2).toString()+" "+table.getValueAt(index, 1).toString());
        uCard.setText(table.getValueAt(index, 3).toString());
        uCharge.setText(table.getValueAt(index, 4).toString());
        uTime.setText(table.getValueAt(index, 5).toString());
        uCredits.setText(table.getValueAt(index, 6).toString());
        }

    private void clearFields() {
    	uId.setText("");
        uName.setText("");
        uCard.setText("");
        uCharge.setText("");
        uTime.setText("");
        uCredits.setText("");
        uId.setText("");
    }

    public UserTable() {
        table = new JTable();
        tableModel = createTableModel();
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.add(table.getTableHeader(), BorderLayout.PAGE_START);
        table.setFillsViewportHeight(true);

        listSelectionModel = table.getSelectionModel();
        table.setSelectionModel(listSelectionModel);
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        table.setSelectionModel(listSelectionModel);

        this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 3;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.ipadx = 2;
    gbc.ipady = 2;
    gbc.weightx = 1;
    gbc.weighty = 1;
        this.add(new JScrollPane(table), gbc);
    }

    private TableModel createTableModel() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] {"User ID", "Last Name", "First Name","Card Type", "Total Use Time", "Total Charges", "Earned Credits"
    				}, 0
        ){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return model;
    }
    
    public JTable getTable() {
    	return this.table;
    }

    

    class SharedListSelectionHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            String contents = "";

            if(lsm.isSelectionEmpty()) {
                System.out.println("<none>");
            } else {
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                if (minIndex==maxIndex) {
                    setFields(minIndex);
                } else {
                    clearFields();
                    for(int i = minIndex; i <= maxIndex; i++) {
                        if(lsm.isSelectedIndex(i)) {
                            for(int j = 0; j < table.getColumnCount(); j++) {
                                contents += table.getValueAt(i, j) + " ";
                            }
                        }
                    }
                    System.out.println(contents);
                }
            }
        }

    }
}
    
    class StationTable extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JTable table;
        private final TableModel tableModel;
        private final ListSelectionModel listSelectionModel;

    private void setFields(int index) {
        sId.setText(table.getValueAt(index, 0).toString());
        sName.setText(table.getValueAt(index, 1).toString());
        sType.setText(table.getValueAt(index, 2).toString());
        sStatus.setText(table.getValueAt(index, 3).toString());
        sMBikes.setText(table.getValueAt(index, 4).toString());
        sEBikes.setText(table.getValueAt(index, 5).toString());
        sParking.setText(table.getValueAt(index, 6).toString());
        sPosition.setText(table.getValueAt(index, 7).toString()+","+table.getValueAt(index, 8).toString());
        rdbtnOnService.setSelected(table.getValueAt(index, 3).toString().equalsIgnoreCase("On service"));
        rdbtnOffline.setSelected(table.getValueAt(index, 3).toString().equalsIgnoreCase("Offline"));
        rdbtnPlusType.setSelected(table.getValueAt(index, 2).toString().equalsIgnoreCase("Plus"));
        rdbtnStandardType.setSelected(table.getValueAt(index, 2).toString().equalsIgnoreCase("Standard"));
        }

    private void clearFields() {
    	sId.setText("");
        sName.setText("");
        sType.setText("");
        sStatus.setText("");
        sMBikes.setText("");
        sEBikes.setText("");
        sParking.setText("");
        sPosition.setText("");
        rdbtnOnService.setSelected(false);
        rdbtnOffline.setSelected(false);
        rdbtnPlusType.setSelected(false);
        rdbtnStandardType.setSelected(false);
    }

    public StationTable() {
        table = new JTable();
        tableModel = createTableModel();
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.add(table.getTableHeader(), BorderLayout.PAGE_START);
        table.setFillsViewportHeight(true);

        listSelectionModel = table.getSelectionModel();
        table.setSelectionModel(listSelectionModel);
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        table.setSelectionModel(listSelectionModel);

        this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 3;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.ipadx = 2;
    gbc.ipady = 2;
    gbc.weightx = 1;
    gbc.weighty = 1;
        this.add(new JScrollPane(table), gbc);
    }

    private TableModel createTableModel() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] {"Station ID", "Name", "Type", "Status", "Mechanical Bikes","Electrical Bikes", "Free Slots","xLocation","yLocation"
    				}, 0
        ){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return model;
    }
    
    public JTable getTable() {
    	return this.table;
    }
    

   

    class SharedListSelectionHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            String contents = "";

            if(lsm.isSelectionEmpty()) {
                System.out.println("<none>");
            } else {
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                if (minIndex==maxIndex) {
                    setFields(minIndex);
                } else {
                    clearFields();
                    for(int i = minIndex; i <= maxIndex; i++) {
                        if(lsm.isSelectedIndex(i)) {
                            for(int j = 0; j < table.getColumnCount(); j++) {
                                contents += table.getValueAt(i, j) + " ";
                            }
                        }
                    }
                    System.out.println(contents);
                }
            }
        }

    }
}
    
    class LocationTable extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JTable table;
        private final TableModel tableModel;
        private final ListSelectionModel listSelectionModel;

    private void setFields(int index) {
        lUser.setText(table.getValueAt(index, 0).toString());
        lPolicy.setText(table.getValueAt(index, 1).toString());
        lDeparture.setText(table.getValueAt(index, 2).toString());
        lStartStation.setText(table.getValueAt(index, 3).toString());
        lStartTime.setText(table.getValueAt(index, 4).toString());
        lArrival.setText(table.getValueAt(index, 5).toString());
        lEndStation.setText(table.getValueAt(index, 6).toString());
        lEndTime.setText(table.getValueAt(index, 7).toString());
        }

    private void clearFields() {
    	uId.setText("");
        uName.setText("");
        uCard.setText("");
        uCharge.setText("");
        uTime.setText("");
        uCredits.setText("");
        uId.setText("");
    }

    public LocationTable() {
        table = new JTable();
        tableModel = createTableModel();
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.add(table.getTableHeader(), BorderLayout.PAGE_START);
        table.setFillsViewportHeight(true);

        listSelectionModel = table.getSelectionModel();
        table.setSelectionModel(listSelectionModel);
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        table.setSelectionModel(listSelectionModel);

        this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 3;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.ipadx = 2;
    gbc.ipady = 2;
    gbc.weightx = 1;
    gbc.weighty = 1;
        this.add(new JScrollPane(table), gbc);
    }

    private TableModel createTableModel() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] {"User ","Bicycle Type","Ride Policy","Departure Position","Departure Station","Departure Time","Arrival Position","Arrival Station","Arrival Time"
    				}, 0
        ){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return model;
    }
    
    public JTable getTable() {
    	return this.table;
    }

    

    class SharedListSelectionHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            String contents = "";

            if(lsm.isSelectionEmpty()) {
                System.out.println("<none>");
            } else {
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                if (minIndex==maxIndex) {
                    setFields(minIndex);
                } else {
                    clearFields();
                    for(int i = minIndex; i <= maxIndex; i++) {
                        if(lsm.isSelectedIndex(i)) {
                            for(int j = 0; j < table.getColumnCount(); j++) {
                                contents += table.getValueAt(i, j) + " ";
                            }
                        }
                    }
                    System.out.println(contents);
                }
            }
        }

    }
}
    
    public static void refreshStations() {
		DefaultTableModel stationModel=new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"Station ID", "Name", "Type", "Status", "Mechanical Bikes","Electrical Bikes", "Free Slots","xLocation","yLocation"
		}
	);
		for (Station stat:Reseau.getInstance().getStationList()) {
				stationModel.addRow(new Object[] {stat.getStationID(),stat.getName(),stat.getTypeStation(),stat.getState(),stat.NumberAvailableBike("Mechanical"),stat.NumberAvailableBike("Electrical"),stat.getFreeSlots(),stat.getPosition().getLatittude(),stat.getPosition().getLongitude()});	
			}
		stationsList.getTable().setModel(stationModel);
	};
	
	public static void refreshUsers() {
		DefaultTableModel userModel=new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"User ID", "Last Name", "First Name","Card Type", "Total Use Time", "Total Charges", "Earned Credits"
		});
		for (User user:Reseau.getInstance().getUserList()) {
			userModel.addRow(new Object[] {user.getUserID(),user.getName(),user.getFirstName(),user.getCard().getTypeCard(), user.getTotalTime(),user.getTotalCharges(),user.getEarnedCredits()});	
		}
		usersList.getTable().setModel(userModel);
	};
	
	public static void refreshLocations() {
		DefaultTableModel locationsModel=new DefaultTableModel(new Object[][] {
			},
				new String[] {
						"User ","Ride Policy","Departure Position","Departure Station","Departure Time","Arrival Position","Arrival Station","Arrival Time"
				});
		for(Location loc : Reseau.getInstance().getLocationList()) {
			locationsModel.addRow(new Object[] {loc.getUser().getUserID(),
					loc.getRidePolicy().getName(),
					loc.getStart(),
					loc.getDeparture().getName(),
					loc.getTimeStart(),
					loc.getEnd(),
					loc.getArrival().getName(),
					loc.getTimeEnd()
					});
		}
		locationsList.getTable().setModel(locationsModel);
	}
	
	public static void refresh() {
		refreshStations();
		refreshUsers();
		refreshLocations();
	}

	public static double getxBoundary() {
		return xBoundary;
	}

	public static void setxBoundary(double xBoundary) {
		GUImainFrame.xBoundary = xBoundary;
	}

	public static double getyBoundary() {
		return yBoundary;
	}

	public static void setyBoundary(double yBoundary) {
		GUImainFrame.yBoundary = yBoundary;
	}
}
