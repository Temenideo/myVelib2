package scenarioTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

<<<<<<< HEAD
import myVelib.GPScoord;
import myVelib.Location;
import myVelib.ParkingSlot;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.TimeState;
import myVelib.User;
import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.AvoidPlus;
import myVelib.ridePolicies.FastestPath;
import myVelib.ridePolicies.PreferPlus;
import myVelib.ridePolicies.RidePolicy;
import myVelib.ridePolicies.ShortestPath;
import myVelib.ridePolicies.Uniformity;

=======
>>>>>>> branch 'master' of https://github.com/Temenideo/myVelib2
public class ScenarioTester {
	public static void executeScenario(String fileName){
		String[] returnValue;
		ArrayList<String> array=new ArrayList<String>();
		FileReader file = null;
		BufferedReader reader = null;
		try { 
			file = new FileReader(fileName); // a FileReader for reading byte
			reader = new BufferedReader(file);
			String line = "";
			while ((line = reader.readLine()) != null) {// read the file line
				returnValue=line.split(";");
				array.clear();
				for (String str:returnValue){
					array.add(str);
				}
				switch(array.get(0)){
				case "reseau": {
<<<<<<< HEAD
					Reseau.getInstance();
					break;
=======
					
>>>>>>> branch 'master' of https://github.com/Temenideo/myVelib2
				}
<<<<<<< HEAD
				
				case "rest":{
					Reseau.getInstance().resetReseau();
					break;
				}
				case "station" : {
					Station stat=new Station(array.get(3),"on service",new GPScoord(Float.parseFloat(array.get(1)), Float.parseFloat(array.get(2))),"station");
					for(int i=0;i<=Integer.parseInt(array.get(5));i++){
						new ParkingSlot(new Electrical(),"Occupied",stat);
					}
					for(int i=0;i<=Integer.parseInt(array.get(6));i++){
						new ParkingSlot(new Mechanical(),"Occupied",stat);
					}
					for(int i=0;i<=Integer.parseInt(array.get(4))-Integer.parseInt(array.get(6))-Integer.parseInt(array.get(5));i++){
						new ParkingSlot(null,"Free",stat);
					}
					break;
				}
				
				case "user" :{
					new User(array.get(1),array.get(2));
					break;
				}
				case "rentabike":{
					Reseau res=Reseau.getInstance();
					Station stat1 = null;
					for(Station stat:res.getStationList()){
						if(stat.getStationID()==Long.parseLong(array.get(2))){
							stat1=stat;
							break;
						}
					}
					for(User user:res.getUserList()){
						if(user.getUserID()==Long.parseLong(array.get(1))){
							Location loc=new Location(user,stat1);
							loc.takeBike(stat1,array.get(3));
							break;
						}
					}
					break;
				}
				case "planningride" :{
					Reseau res=Reseau.getInstance();
					RidePolicy ridePolicy=null;
					Location loc=null;
					switch(array.get(6)){
					case "ShortestPath": ridePolicy=new ShortestPath();
					case "AvoidPlus": ridePolicy=new AvoidPlus();
					case "FastestPath" : ridePolicy=new FastestPath();
					case "PreferPlus" : ridePolicy=new PreferPlus();
					case "Uniformity" : ridePolicy=new Uniformity();
					}
					for(User user:res.getUserList()){
						if(user.getUserID()==Long.parseLong(array.get(1))){
							loc=new Location(user,new GPScoord(Float.parseFloat(array.get(2)),Float.parseFloat(array.get(3))),
									new GPScoord(Float.parseFloat(array.get(4)),Float.parseFloat(array.get(5))),ridePolicy,array.get(7));
							break;
						}
					}
					loc.takeBike(loc.getDeparture(),array.get(7));
					break;
				}
				case "returnbike" :{
					Reseau res=Reseau.getInstance();
					Station stat1 = null;
					for(Station stat:res.getStationList()){
						if(stat.getStationID()==Long.parseLong(array.get(1))){
							stat1=stat;
							break;
						}
					}
					for(User user:res.getUserList()){
						if(user.getUserID()==Long.parseLong(array.get(2))){
							user.getLoc().returnBike(stat1);
							break;
						}
					}
					break;
				}
				case "offline":{
					Reseau res=Reseau.getInstance();
					Station stat1 = null;
					for(Station stat:res.getStationList()){
						if(stat.getStationID()==Long.parseLong(array.get(1))){
							stat.setState("Offline");
							break;
						}
					}
					break;
				}
				case "simulationlocation" :{
					Reseau res=Reseau.getInstance();
					Station statdepart = null;
					Station statarrivée = null;
					ParkingSlot ps=null;
					User user1=null;
					for(Station stat:res.getStationList()){
						if(stat.getStationID()==Long.parseLong(array.get(2))){
							statdepart=stat;
							break;
						}
					}
					for(Station stat:res.getStationList()){
						if(stat.getStationID()==Long.parseLong(array.get(3))){
							statarrivée=stat;
							break;
						}
					}
					for(User user:res.getUserList()){
						if(user.getUserID()==Long.parseLong(array.get(1))){
							user1=user;
							break;
						}
					}
					Location loc=new Location(user1,statdepart);
					loc.takeBike(statdepart, array.get(5));
					Date datetempo=Calendar.getInstance().getTime();
					datetempo.setMinutes(datetempo.getMinutes()-Integer.parseInt(array.get(4)));
					loc.setTimeStart(datetempo);
					if(array.get(5)=="Mechanical"){
						ps=statdepart.getParkingSlotList().get(5);
						ps.getHistory().remove(ps.getHistory().size());
						ps.getHistory().add(new TimeState(false,datetempo));
					}
					else {
						ps=statdepart.getParkingSlotList().get(0);
						ps.getHistory().remove(ps.getHistory().size());
						ps.getHistory().add(new TimeState(false,datetempo));
					}
					loc.returnBike(statarrivée);
				break;
				}
=======
					
>>>>>>> branch 'master' of https://github.com/Temenideo/myVelib2
				}
			}
		}
<<<<<<< HEAD


=======
>>>>>>> branch 'master' of https://github.com/Temenideo/myVelib2
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally {
			if(reader!=null){
				try{reader.close();}
				catch(IOException e){}
			}
			if(file!=null){
				try{file.close();}
				catch(IOException e){}
			}
		}
	}
	public static void main(String[] args) {
		executeScenario("testScenario1.txt");
	}
}
