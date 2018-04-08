package myVelib.gui;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import myVelib.BadParkingSlotCreationException;
import myVelib.GPScoord;
import myVelib.Location;
import myVelib.Reseau;
import myVelib.User;
import myVelib.ridePolicies.AvoidPlus;
import myVelib.ridePolicies.FastestPath;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
import myVelib.ridePolicies.PreferPlus;
import myVelib.ridePolicies.RidePolicy;
import myVelib.ridePolicies.ShortestPath;
import myVelib.ridePolicies.Uniformity;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Choice;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class GUILocation extends JFrame{
	public GUILocation() {
		setType(Type.UTILITY);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Location Creation");
		getContentPane().setLayout(null);
		
		JPanel arrivalPanel = new JPanel();
		arrivalPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Arrival Position", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		arrivalPanel.setBounds(340, 155, 330, 75);
		getContentPane().add(arrivalPanel);
		arrivalPanel.setLayout(null);
		
		JPanel endXpanel = new JPanel();
		endXpanel.setBounds(10, 18, 145, 50);
		arrivalPanel.add(endXpanel);
		endXpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "X coordinate", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		endXpanel.setLayout(null);
		
		JSlider endXslider = new JSlider();
		endXslider.setValue(0);
		endXslider.setPaintLabels(true);
		endXslider.setSnapToTicks(true);
		endXslider.setMaximum((int) (GUImainFrame.xBoundary/2));
		endXslider.setMinimum(-(int) (GUImainFrame.xBoundary/2));
		endXslider.setBounds(8, 20, 130, 25);
		endXpanel.add(endXslider);
		
		JPanel endYpanel = new JPanel();
		endYpanel.setBounds(170, 18, 145, 50);
		arrivalPanel.add(endYpanel);
		endYpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Y coordinate", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		endYpanel.setLayout(null);
		
		JSlider endYslider = new JSlider();
		endYslider.setPaintLabels(true);
		endYslider.setSnapToTicks(true);
		endYslider.setValue(0);
		endYslider.setMinimum(-(int) (GUImainFrame.yBoundary/2));
		endYslider.setMaximum((int) (GUImainFrame.yBoundary/2));
		endYslider.setBounds(8, 20, 130, 25);
		endYpanel.add(endYslider);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(400, 265, 100, 25);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 265, 100, 25);
		getContentPane().add(btnCancel);
		
		JPanel userPanel = new JPanel();
		userPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User Selection", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		userPanel.setBounds(10, 20, 180, 47);
		getContentPane().add(userPanel);
		userPanel.setLayout(null);
		
		Choice userChoice = new Choice();
		userChoice.setBounds(6, 18, 165, 22);
		for (User user : Reseau.getInstance().getUserList()) {
			userChoice.add(user.getUserID()+". "+user.getFirstName()+" "+user.getName());
		}
		userPanel.add(userChoice);
		
		JPanel ridePolicyPanel = new JPanel();
		ridePolicyPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ride Policy", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ridePolicyPanel.setBounds(332, 20, 330, 120);
		getContentPane().add(ridePolicyPanel);
		ridePolicyPanel.setLayout(null);
		
		JRadioButton chckbxAvoidPlusStations = new JRadioButton("Avoid Plus Stations");
		chckbxAvoidPlusStations.setBounds(175, 20, 150, 25);
		ridePolicyPanel.add(chckbxAvoidPlusStations);
		ridePolicyGroup.add(chckbxAvoidPlusStations);
		
		JRadioButton chckbxPreferPlusStations = new JRadioButton("Prefer Plus Stations");
		chckbxPreferPlusStations.setBounds(175, 50, 150, 25);
		ridePolicyPanel.add(chckbxPreferPlusStations);
		ridePolicyGroup.add(chckbxPreferPlusStations);
		
		JRadioButton chckbxStationUniformity = new JRadioButton("Station Uniformity");
		chckbxStationUniformity.setBounds(175, 80, 150, 25);
		ridePolicyPanel.add(chckbxStationUniformity);
		ridePolicyGroup.add(chckbxStationUniformity);
		
		JRadioButton chckbxFastestPath = new JRadioButton("Fastest Path");
		chckbxFastestPath.setSelected(true);
		chckbxFastestPath.setBounds(15, 20, 150, 25);
		ridePolicyPanel.add(chckbxFastestPath);
		ridePolicyGroup.add(chckbxFastestPath);
		
		JRadioButton chckbxShortestPath = new JRadioButton("Shortest Path");
		chckbxShortestPath.setBounds(15, 50, 150, 25);
		ridePolicyPanel.add(chckbxShortestPath);
		ridePolicyGroup.add(chckbxShortestPath);
		
		JPanel bikeTypePanel = new JPanel();
		bikeTypePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bicycle Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bikeTypePanel.setBounds(200, 20, 120, 98);
		getContentPane().add(bikeTypePanel);
		bikeTypePanel.setLayout(null);
		
		JRadioButton rdbtnElectrical = new JRadioButton("Electrical");
		bikeTypeGroup.add(rdbtnElectrical);
		rdbtnElectrical.setBounds(10, 60, 100, 25);
		bikeTypePanel.add(rdbtnElectrical);
		
		JRadioButton rdbtnMechanical = new JRadioButton("Mechanical");
		rdbtnMechanical.setSelected(true);
		bikeTypeGroup.add(rdbtnMechanical);
		rdbtnMechanical.setBounds(10, 25, 100, 25);
		bikeTypePanel.add(rdbtnMechanical);
		
		JPanel startPanel = new JPanel();
		startPanel.setLayout(null);
		startPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Starting Position", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		startPanel.setBounds(10, 155, 330, 75);
		getContentPane().add(startPanel);
		
		JPanel startXpanel = new JPanel();
		startXpanel.setLayout(null);
		startXpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "X coordinate", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		startXpanel.setBounds(10, 18, 145, 50);
		startPanel.add(startXpanel);
		
		JSlider startXslider = new JSlider();
		startXslider.setValue(0);
		startXslider.setSnapToTicks(true);
		startXslider.setPaintLabels(true);
		startXslider.setMinimum(-(int) (GUImainFrame.xBoundary/2));
		startXslider.setMaximum((int) (GUImainFrame.xBoundary/2));
		startXslider.setBounds(8, 20, 130, 25);
		startXpanel.add(startXslider);
		
		JPanel startYpanel = new JPanel();
		startYpanel.setLayout(null);
		startYpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Y coordinate", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		startYpanel.setBounds(170, 18, 145, 50);
		startPanel.add(startYpanel);
		
		JSlider startYslider = new JSlider();
		startYslider.setValue(0);
		startYslider.setSnapToTicks(true);
		startYslider.setPaintLabels(true);
		startYslider.setMinimum(-(int) (GUImainFrame.yBoundary/2));
		startYslider.setMaximum((int) (GUImainFrame.yBoundary/2));
		startYslider.setBounds(8, 20, 130, 25);
		startYpanel.add(startYslider);
		
		JButton btnStart = new JButton("Confirm and Start");
		btnStart.setBounds(520, 265, 150, 25);
		getContentPane().add(btnStart);
	
	ActionListener confirm = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			GPScoord start = new GPScoord (startXslider.getValue(),startYslider.getValue());
			GPScoord end = new GPScoord (endXslider.getValue(),endYslider.getValue());
			User user = Reseau.getInstance().getUserList().get(userChoice.getSelectedIndex());
			String bikeType = "Mechanical";
			if(rdbtnElectrical.isSelected()) {
				bikeType="Electrical";
			}
			RidePolicy policy = new FastestPath();
			if(chckbxShortestPath.isSelected()) {
				policy = new ShortestPath();
			}
			else if(chckbxStationUniformity.isSelected()) {
				policy = new Uniformity();
			}
			else if(chckbxAvoidPlusStations.isSelected()) {
				policy = new AvoidPlus();
			}
			else if(chckbxPreferPlusStations.isSelected()) {
				policy = new PreferPlus();
			}
			try {
				@SuppressWarnings("unused")
				Location loc = new Location(user,start,end,policy,bikeType);
			} catch (NoEndStationAvailableException | NoStartStationAvailableException e1) {
				
				e1.printStackTrace();
			}
			System.out.println("Location has been successfully planned");
			GUImainFrame.refresh();
			dispose();
		}
	};
	
	ActionListener confirmStart = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			GPScoord start = new GPScoord (startXslider.getValue(),startYslider.getValue());
			GPScoord end = new GPScoord (endXslider.getValue(),endYslider.getValue());
			User user = Reseau.getInstance().getUserList().get(userChoice.getSelectedIndex());
			String bikeType = "Mechanical";
			if(rdbtnElectrical.isSelected()) {
				bikeType="Electrical";
			}
			RidePolicy policy = new FastestPath();
			if(chckbxShortestPath.isSelected()) {
				policy = new ShortestPath();
			}
			else if(chckbxStationUniformity.isSelected()) {
				policy = new Uniformity();
			}
			else if(chckbxAvoidPlusStations.isSelected()) {
				policy = new AvoidPlus();
			}
			else if(chckbxPreferPlusStations.isSelected()) {
				policy = new PreferPlus();
			}
			try {
				Location loc = new Location(user,start,end,policy,bikeType);
				System.out.println("Location has been successfully planned");
				loc.takeBike(loc.getDeparture(), bikeType);
			} catch (NoEndStationAvailableException | NoStartStationAvailableException | BadParkingSlotCreationException e1) {
				
				e1.printStackTrace();
			}
			GUImainFrame.refresh();
			dispose();
		}
	};
	
	ActionListener cancel = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			dispose();
		}
		
	};
	
	btnConfirm.addActionListener(confirm);	
	btnCancel.addActionListener(cancel);
	btnStart.addActionListener(confirmStart);
	
	
	setSize(700,350);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ButtonGroup ridePolicyGroup = new ButtonGroup();
	private final ButtonGroup bikeTypeGroup = new ButtonGroup();
}
