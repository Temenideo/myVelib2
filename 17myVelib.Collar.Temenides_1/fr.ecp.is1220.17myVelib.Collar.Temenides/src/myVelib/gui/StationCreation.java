package myVelib.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.GPScoord;
import myVelib.ParkingSlot;
import myVelib.Station;
import myVelib.Bicycle.BicycleFactory;
import myVelib.ridePolicies.NoEndStationAvailableException;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;

public class StationCreation extends JFrame{
	public StationCreation() {
		setType(Type.UTILITY);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Station creation");
		getContentPane().setLayout(null);
		
		txtStationName = new JTextField();
		txtStationName.setBounds(55, 15, 295, 25);
		txtStationName.setText("Station Name");
		getContentPane().add(txtStationName);
		txtStationName.setColumns(10);
		
		JPanel Xpanel = new JPanel();
		Xpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "X coordinate", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Xpanel.setBounds(55, 65, 145, 50);
		getContentPane().add(Xpanel);
		Xpanel.setLayout(null);
		
		JSlider XCoordinate = new JSlider();
		XCoordinate.setValue(0);
		XCoordinate.setPaintLabels(true);
		XCoordinate.setSnapToTicks(true);
		XCoordinate.setMaximum((int) (GUImainFrame.xBoundary/2));
		XCoordinate.setMinimum(-(int) (GUImainFrame.xBoundary/2));
		XCoordinate.setBounds(8, 20, 130, 25);
		Xpanel.add(XCoordinate);
		
		JPanel Ypanel = new JPanel();
		Ypanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Y coordinate", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Ypanel.setBounds(205, 65, 145, 50);
		getContentPane().add(Ypanel);
		Ypanel.setLayout(null);
		
		JSlider YCoordinate = new JSlider();
		YCoordinate.setPaintLabels(true);
		YCoordinate.setSnapToTicks(true);
		YCoordinate.setValue(0);
		YCoordinate.setMinimum(-(int) (GUImainFrame.yBoundary/2));
		YCoordinate.setMaximum((int) (GUImainFrame.yBoundary/2));
		YCoordinate.setBounds(8, 20, 130, 25);
		Ypanel.add(YCoordinate);
		
		JPanel parkingSlotNumber = new JPanel();
		parkingSlotNumber.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Number of parking slots", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		parkingSlotNumber.setBounds(55, 112, 295, 50);
		getContentPane().add(parkingSlotNumber);
		parkingSlotNumber.setLayout(null);
		
		JSpinner psNumberSpinner = new JSpinner();
		psNumberSpinner.setBounds(40, 18, 230, 22);
		parkingSlotNumber.add(psNumberSpinner);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(325, 220, 100, 25);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(5, 220, 100, 25);
		getContentPane().add(btnCancel);
		
		JCheckBox rdbtnOnService = new JCheckBox("On service");
		rdbtnOnService.setBounds(60, 40, 130, 25);
		getContentPane().add(rdbtnOnService);
		
		JCheckBox rdbtnPlusStation = new JCheckBox("Plus station");
		rdbtnPlusStation.setBounds(220, 40, 130, 25);
		getContentPane().add(rdbtnPlusStation);
		
		JPanel elecBike = new JPanel();
		elecBike.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Electrical Bike %", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		elecBike.setBounds(210, 165, 140, 51);
		getContentPane().add(elecBike);
		elecBike.setLayout(null);
		
		JSlider elecBikeSlider = new JSlider();
		elecBikeSlider.setValue(30);
		elecBikeSlider.setPaintLabels(true);
		elecBikeSlider.setBounds(6, 18, 130, 26);
		elecBike.add(elecBikeSlider);
		elecBikeSlider.setToolTipText("Electrical bike %");
		
		JPanel bikeNumber = new JPanel();
		bikeNumber.setLayout(null);
		bikeNumber.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Occupied Slots %", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bikeNumber.setBounds(55, 165, 140, 51);
		getContentPane().add(bikeNumber);
		
		JSlider bikeNumberSlider = new JSlider();
		bikeNumberSlider.setValue(70);
		bikeNumberSlider.setToolTipText("Occupied slots ");
		bikeNumberSlider.setPaintLabels(true);
		bikeNumberSlider.setBounds(6, 18, 130, 26);
		bikeNumber.add(bikeNumberSlider);
	
	ActionListener confirm = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			try {
				Station stat =createStation();
				System.out.println("Added Station "+stat.getStationID()+" : "+stat.getName()+" to MyVelib Network");
			} catch (BadStateStationCreationException | BadTypeStationCreationException
					| BadParkingSlotCreationException | NoEndStationAvailableException e1) {
				e1.printStackTrace();
			}
			GUImainFrame.refreshStations();
			dispose();
			}
		public Station createStation() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
			String name = txtStationName.getText();
			GPScoord position = new GPScoord((float)XCoordinate.getValue(),(float)YCoordinate.getValue());
			String stationType = "Standard";
			if (rdbtnPlusStation.isSelected()) {
				stationType = "Plus";
				}
			String state = "Offline";
			if (rdbtnOnService.isSelected()) {
				state = "On service";
				}
			int psNumber = (int)psNumberSpinner.getValue();
			Station stat = new Station(new ArrayList<ParkingSlot> (), stationType, state, position, name);
			int bikes = (int)psNumber*bikeNumberSlider.getValue()/100;
			int elec = (int)bikes*elecBikeSlider.getValue()/100;
			int b=0; int e=0;
			for (int i=0;i<psNumber;i++) {
				if(b<bikes) {
					if(e<elec) {
						new ParkingSlot(BicycleFactory.bike("Electrical"), "Occupied", stat);
						e++;
						b++;
					}
					else {
						new ParkingSlot(BicycleFactory.bike("Mechanical"), "Occupied", stat);
						b++;
					}
					
				}
				else {new ParkingSlot(null, "Free", stat);
			}
		}
	return stat;
	}
		
	};
	
	ActionListener cancel = new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			dispose();
		}
		
	};
	
	btnConfirm.addActionListener(confirm);	
	btnCancel.addActionListener(cancel);
	
	
	setSize(450,300);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtStationName;
	
	
}
