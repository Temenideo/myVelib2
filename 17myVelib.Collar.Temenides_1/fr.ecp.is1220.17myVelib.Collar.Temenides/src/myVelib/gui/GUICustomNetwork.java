package myVelib.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.setup;
import myVelib.ridePolicies.NoEndStationAvailableException;
import javax.swing.SwingConstants;

public class GUICustomNetwork extends JFrame{
	public GUICustomNetwork() {
		setType(Type.UTILITY);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Custom Network");
		getContentPane().setLayout(null);
		
		JPanel namePanel = new JPanel();
		namePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Network Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		namePanel.setBounds(55, 15, 410, 50);
		getContentPane().add(namePanel);
		namePanel.setLayout(null);
		
		txtStationName = new JTextField();
		txtStationName.setHorizontalAlignment(SwingConstants.CENTER);
		txtStationName.setBounds(11, 18, 390, 25);
		namePanel.add(txtStationName);
		txtStationName.setText("myVelib");
		txtStationName.setColumns(10);
		
		JPanel Xpanel = new JPanel();
		Xpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "X boundary (meters)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Xpanel.setBounds(55, 65, 200, 50);
		getContentPane().add(Xpanel);
		Xpanel.setLayout(null);
		
		JSpinner xSpinner = new JSpinner();
		xSpinner.setBounds(20, 19, 165, 22);
		xSpinner.setValue(4000);
		Xpanel.add(xSpinner);
		
		JPanel Ypanel = new JPanel();
		Ypanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Y boundary (meters)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Ypanel.setBounds(265, 65, 200, 50);
		getContentPane().add(Ypanel);
		Ypanel.setLayout(null);
		
		JSpinner ySpinner = new JSpinner();
		ySpinner.setBounds(20, 19, 165, 22);
		ySpinner.setValue(4000);
		Ypanel.add(ySpinner);
		
		JPanel stationNumber = new JPanel();
		stationNumber.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Number of stations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		stationNumber.setBounds(55, 128, 410, 50);
		getContentPane().add(stationNumber);
		stationNumber.setLayout(null);
		
		JSpinner stationNumberSpinner = new JSpinner();
		stationNumberSpinner.setBounds(20, 18, 370, 22);
		stationNumberSpinner.setValue(10);
		stationNumber.add(stationNumberSpinner);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(402, 318, 120, 25);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 318, 120, 25);
		getContentPane().add(btnCancel);
		
		JPanel elecBike = new JPanel();
		elecBike.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Electrical Bike %", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		elecBike.setBounds(265, 254, 200, 51);
		getContentPane().add(elecBike);
		elecBike.setLayout(null);
		
		JSlider elecBikeSlider = new JSlider();
		elecBikeSlider.setValue(30);
		elecBikeSlider.setPaintLabels(true);
		elecBikeSlider.setBounds(20, 18, 165, 26);
		elecBike.add(elecBikeSlider);
		elecBikeSlider.setToolTipText("Electrical bike %");
		
		JPanel bikeNumber = new JPanel();
		bikeNumber.setLayout(null);
		bikeNumber.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Occupied Slots %", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bikeNumber.setBounds(55, 254, 200, 51);
		getContentPane().add(bikeNumber);
		
		JSlider bikeNumberSlider = new JSlider();
		bikeNumberSlider.setValue(70);
		bikeNumberSlider.setToolTipText("Occupied slots ");
		bikeNumberSlider.setPaintLabels(true);
		bikeNumberSlider.setBounds(20, 18, 165, 26);
		bikeNumber.add(bikeNumberSlider);
		
		JPanel slotPanel = new JPanel();
		slotPanel.setLayout(null);
		slotPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Number of Parking Slots (per Station)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		slotPanel.setBounds(55, 191, 410, 50);
		getContentPane().add(slotPanel);
		
		JSpinner slotSpinner = new JSpinner();
		slotSpinner.setBounds(20, 18, 370, 22);
		slotSpinner.setValue(10);
		slotPanel.add(slotSpinner);
	
	ActionListener confirm = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			try {
				int stationNumber = (int) stationNumberSpinner.getValue();
				int slotperStation = (int) slotSpinner.getValue();
				double xBoundary = (double)(int) xSpinner.getValue();
				double yBoundary = (double)(int) ySpinner.getValue();
				double bikeNumber = (double)(int) bikeNumberSlider.getValue()/100;
				double elecBike = (double)(int) elecBikeSlider.getValue()/100;
				setup.startMyVelib(stationNumber,slotperStation,xBoundary,yBoundary,bikeNumber,elecBike);
				GUImainFrame.setxBoundary(xBoundary);
				GUImainFrame.setyBoundary(yBoundary);
			} catch (BadStateStationCreationException | BadTypeStationCreationException
					| BadParkingSlotCreationException | NoEndStationAvailableException e1) {
				e1.printStackTrace();
			}
			GUImainFrame mainFrame= new GUImainFrame();			
			mainFrame.setVisible(true);
			mainFrame.setTitle(txtStationName.getText());
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
	
	
	setSize(550,400);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtStationName;
}
